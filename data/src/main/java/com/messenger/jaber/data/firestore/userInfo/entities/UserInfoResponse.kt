package com.messenger.jaber.data.firestore.userInfo.entities

data class UserInfoResponse(
    val uid: String = "",
    val fcmToken: String = "",
    val phone: String = "",
    val login: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val avatarUrl: String? = "",
    val bio: String? = null,
    val lastSeen: Long = 0L,
    val createdAt: Long = 0L
)