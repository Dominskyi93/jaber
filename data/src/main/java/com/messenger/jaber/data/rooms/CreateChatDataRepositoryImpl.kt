package com.messenger.jaber.data.rooms

import com.elveum.container.unwrap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.CreateChatDataRepository
import com.messenger.jaber.data.rooms.entities.RoomDataEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateChatDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    auth: FirebaseAuth
) : CreateChatDataRepository {
    val currentUserUId = auth.currentUser?.uid ?: ""
    override suspend fun createChat(title: String, vararg userUIds: String) {
        containerOf {
            val memberList = listOf(currentUserUId) + userUIds
            firestore
                .collection("chats")
                .add(
                    RoomDataEntity.Default(
                        title = title,
                        userIds = memberList
                    )
                )
        }.unwrap()
    }
}