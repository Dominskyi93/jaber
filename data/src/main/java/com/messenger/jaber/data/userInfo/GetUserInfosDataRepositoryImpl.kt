package com.messenger.jaber.data.userInfo

import com.elveum.container.unwrap
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.GetUserInfosDataRepository
import com.messenger.jaber.data.userInfo.entities.UserInfoRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserInfosDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : GetUserInfosDataRepository {

    override suspend fun findUsersByEmail(email: String): UserInfoRequest? {
        return containerOf {
            val snapshot = firestore
                .collection("users")
                .whereEqualTo("login", email)
                .get()
                .await()

            snapshot.documents
                .firstOrNull()
                ?.toObject(UserInfoRequest::class.java)
        }.unwrap()
    }
}
