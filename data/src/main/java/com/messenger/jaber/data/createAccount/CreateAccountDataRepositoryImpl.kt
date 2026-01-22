package com.messenger.jaber.data.createAccount

import com.elveum.container.mapException
import com.elveum.container.unwrap
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.CreateAccountDataRepository
import com.messenger.jaber.features.signup.domain.exceptions.LoginAlreadyExistException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CreateAccountDataRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : CreateAccountDataRepository {
    override suspend fun createAccount(email: String, password: String): String {
        return containerOf {
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            result.user!!.uid
        }.mapException(Exception::class) { e ->
            val cause = e.cause
            if (cause is FirebaseAuthUserCollisionException) {
                LoginAlreadyExistException(email)
            } else {
                e
            }
        }.unwrap()
    }
}