package com.messenger.jaber.data

interface CreateAccountDataRepository {
    suspend fun createAccount(email: String, password: String): String
}