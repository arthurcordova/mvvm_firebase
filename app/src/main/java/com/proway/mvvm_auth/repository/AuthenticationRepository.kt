package com.proway.mvvm_auth.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository {

    private val auth = FirebaseAuth.getInstance()

    fun signInWithEmailPassword(
        email: String, password: String,
        callback: (FirebaseUser?, String?) -> Unit
    ) {
        val task = auth.signInWithEmailAndPassword(email, password)
        task.addOnSuccessListener { authResult ->
            if (authResult.user != null) {
                callback(authResult.user, null)
            } else {
                callback(null, "Erro no login")
            }
        }
        task.addOnFailureListener {
            callback(null, it.message)
        }
    }

    /**
     * Function para criar um novo user no Firebase
     * retorna o User dentro da closure
     */
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

    fun signOut() {
        auth.signOut()
    }

    fun currentUser(): FirebaseUser? = auth.currentUser


}