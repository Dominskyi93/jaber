package com.messenger.jaber.data.rooms

import com.elveum.container.ListContainerFlow
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.data.RoomsDataRepository
import com.messenger.jaber.data.rooms.entities.RoomDataEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomsDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RoomsDataRepository {
    override fun getRooms(): ListContainerFlow<RoomDataEntity> = callbackFlow {
        val listenerRegistration = firestore
            .collection("chats")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    trySend(errorContainer(error))
                    return@addSnapshotListener
                }

                val data = snapshot?.documents
                    ?.mapNotNull { it.toObject(RoomDataEntity.Default::class.java) }
                    ?: emptyList()

                trySend(successContainer(data))
            }

        awaitClose { listenerRegistration.remove() }
    }
}