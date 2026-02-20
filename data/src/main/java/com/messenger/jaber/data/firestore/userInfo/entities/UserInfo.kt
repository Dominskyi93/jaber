package com.messenger.jaber.data.firestore.userInfo.entities

interface UserInfo {
    val uid: String
    val fcmToken: String
    val phone: String
    val login: String
    val firstName: String
    val lastName: String
    val avatarUrl: String?
    val bio: String?
    val lastSeen: Long
    val createdAt: Long

    data class Default(
        override val avatarUrl: String? = null,
        override val uid: String = "",
        override val fcmToken: String = "",
        override val phone: String = "",
        override val login: String = "",
        override val firstName: String = "",
        override val lastName: String = "",
        override val bio: String? = null,
        override val lastSeen: Long = now,
        override val createdAt: Long = now
    ) : UserInfo

    companion object {
        val now = System.currentTimeMillis()
    }
}