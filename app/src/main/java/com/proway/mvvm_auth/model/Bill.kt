package com.proway.mvvm_auth.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot

data class Bill(
    var uid: String?,
    val name: String?,
    val price: Double?
) {

    companion object {

        fun fromData(snapshot: QueryDocumentSnapshot): Bill {
            return Bill(
                uid = snapshot.id,
                name = snapshot.data["name"] as? String,
                price = snapshot.data["price"] as? Double
            )
        }

        fun fromDocument(doc: DocumentSnapshot): Bill {

            return Bill(
                uid = doc.id,
                name = doc["name"] as? String,
                price = doc["price"] as? Double
            )
        }

    }

}
