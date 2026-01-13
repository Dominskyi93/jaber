package com.messenger.jaber.data.createAccount

import com.google.firebase.auth.FirebaseAuth
import com.messenger.jaber.data.CreateAccountDataRepository
import javax.inject.Inject

class CreateAccountDataRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : CreateAccountDataRepository {
    override suspend fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }
}