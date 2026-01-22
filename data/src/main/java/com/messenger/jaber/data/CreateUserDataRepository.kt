package com.messenger.jaber.data

import com.messenger.jaber.data.firestore.userData.entities.UserData

interface CreateUserDataRepository {
    suspend fun createUser(userData: UserData.Default, uid: String)
}