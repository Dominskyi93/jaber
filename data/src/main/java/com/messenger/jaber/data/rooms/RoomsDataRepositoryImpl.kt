package com.messenger.jaber.data.rooms

import com.elveum.container.ListContainerFlow
import com.elveum.container.containerOf
import com.elveum.container.successContainer
import com.elveum.container.unwrap
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.data.RoomsDataRepository
import com.messenger.jaber.data.rooms.entities.RoomDataEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomsDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : RoomsDataRepository {
    override fun getRooms(): ListContainerFlow<RoomDataEntity> = flow {
        val result = successContainer(
            firestore.collection("chats")
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(RoomDataEntity.Default::class.java) }
        )
        emit(result)
    }
}