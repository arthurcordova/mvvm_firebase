package com.proway.mvvm_auth.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository {

    private val auth = FirebaseAuth.getInstance()

//    fun signInWithEmailPassword(email: String, password: String) {
//
//    }

    fun createAccountWithEmailPassword(
        email: String,
        password: String,
        callback: (FirebaseUser?) -> Unit
    ) {
        val task = auth.createUserWithEmailAndPassword(email, password)
        task.addOnSuccessListener { authResult ->
            callback(authResult.user)
        }
    }

}