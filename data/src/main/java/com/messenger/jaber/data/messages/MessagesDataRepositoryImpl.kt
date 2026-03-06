package com.messenger.jaber.data.messages

import com.elveum.container.ListContainerFlow
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.data.MessagesDataRepository
import com.messenger.jaber.data.messages.entities.MessageDataEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class MessagesDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : MessagesDataRepository {
    val myUid = auth.currentUser?.uid ?: ""

    override fun getMessages(chatId: String): ListContainerFlow<MessageDataEntity> = callbackFlow {

        val listenerRegistration = firestore
            .collection("chats")
            .document(chatId)
            .collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    trySend(errorContainer(error))
                    return@addSnapshotListener
                }

                val data = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(MessageDataEntity.Default::class.java)?.copy(
                        id = doc.id,
                        isMyMessage = myUid == doc.getString("senderId")
                    )
                } ?: emptyList()

                trySend(successContainer(data))
            }

        awaitClose { listenerRegistration.remove() }
    }
}