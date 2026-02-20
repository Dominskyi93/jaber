package com.messenger.jaber.data

import com.messenger.jaber.data.firestore.userInfo.entities.UserInfo
import kotlinx.coroutines.flow.Flow

interface GetUsersDataRepository {
    fun findUsersByEmail(email: String): Flow<UserInfo>
}