package com.messenger.jaber.data.firebaseAuth

import android.util.Log
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
    override suspend fun signIn(credentials: AuthDataCredentials): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(credentials.login, credentials.password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}