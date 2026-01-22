package com.messenger.jaber.data.firestore.userData

import android.util.Log
import com.elveum.container.unwrap
import com.google.firebase.firestore.FirebaseFirestore
import com.messenger.jaber.core.data.network.containerOf
import com.messenger.jaber.data.CreateUserDataRepository
import com.messenger.jaber.data.firestore.userData.entities.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateUserDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CreateUserDataRepository {
    override suspend fun createUser(userData: UserData.Default, uid: String) {
        containerOf {
            Log.d("myTag", "uid = $uid")
            firestore.collection("users")
                .document()
                .set(userData)
        }.unwrap()

    }
}