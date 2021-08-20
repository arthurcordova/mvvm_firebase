package com.proway.mvvm_auth.model

import android.text.TextUtils
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.*

data class Bill(
    var uid: String?,
    val name: String?,
    val price: Double?
) {

    fun validarConta(): Boolean {
        if (price == null) {
            return false
        }
        if (price < 0) {
            return false
        }
        return true
    }

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
