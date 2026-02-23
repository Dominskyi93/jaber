package com.messenger.jaber.data.userInfo

import com.elveum.container.unwrap
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.CreateUserInfoDataRepository
import com.messenger.jaber.data.userInfo.entities.UserInfoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateUserInfoDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CreateUserInfoDataRepository {
    override suspend fun createUserInfo(userInfoResponse: UserInfoResponse) {
        containerOf {
            firestore
                .collection("users")
                .add(userInfoResponse)
        }.unwrap()
    }
}