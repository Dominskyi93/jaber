package com.messenger.jaber.data.firebaseAuth

import com.google.firebase.auth.FirebaseAuth
import com.messenger.jaber.data.FirebaseAuthDataRepository
import com.messenger.jaber.data.accounts.entities.AuthDataCredentials
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthDataRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthDataRepository {
    override suspend fun signIn(credentials: AuthDataCredentials): Result<String> {
        return try {
            val authResult =
                auth.signInWithEmailAndPassword(credentials.login, credentials.password).await()
            val uid = authResult.user?.uid ?: ""
            Result.success(uid)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}