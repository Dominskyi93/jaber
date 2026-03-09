@file:OptIn(ExperimentalCoroutinesApi::class)

package com.messenger.jaber.data.rooms

import com.elveum.container.ListContainerFlow
import com.elveum.container.containerUpdate
import com.elveum.container.errorContainer
import com.elveum.container.pendingContainer
import com.elveum.container.successContainer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.data.RoomsDataRepository
import com.messenger.jaber.data.rooms.entities.RoomDataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomsDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val scope: CoroutineScope,
    auth: FirebaseAuth
) : RoomsDataRepository {
    val currentUserUid = auth.currentUser?.uid
    private val triggerFlow = MutableStateFlow(Trigger())

    private val roomsStateFlow = triggerFlow
        .flatMapLatest {
            createLoadRoomsFlow()
        }
        .containerUpdate {
            reloadFunction = ::reload
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = 1000,
                replayExpirationMillis = 5000
            ),
            initialValue = pendingContainer()
        )


    override fun getRooms(): ListContainerFlow<RoomDataEntity> {
        return roomsStateFlow
    }

    fun reload(silently: Boolean) {
        triggerFlow.update { Trigger(silently) }
    }

    private fun createLoadRoomsFlow() = callbackFlow {
        trySend(pendingContainer())
        val listenerRegistration = firestore
            .collection("chats")
            .whereArrayContains("userIds", "$currentUserUid")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    trySend(errorContainer(error))
                    return@addSnapshotListener
                }

                val data = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(RoomDataEntity.Default::class.java)?.copy(
                        id = doc.id
                    )
                } ?: emptyList()

                trySend(successContainer(data))
            }

        awaitClose { listenerRegistration.remove() }
    }

    class Trigger(val silently: Boolean = false)
}