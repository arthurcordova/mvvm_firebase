package com.proway.mvvm_auth.model

import com.google.firebase.firestore.QueryDocumentSnapshot

data class Conta(
    val uid: String?,
    val name: String?,
    val price: Double?
) {

    companion object {

        fun fromData(snapshot: QueryDocumentSnapshot): Conta {
            return Conta(
                uid = snapshot.id,
                name = snapshot.data["name"] as? String,
                price = snapshot.data["price"] as? Double
            )
        }

    }

}
