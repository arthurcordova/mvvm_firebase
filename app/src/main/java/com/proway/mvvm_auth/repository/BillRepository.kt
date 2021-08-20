package com.proway.mvvm_auth.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.model.PokeResponse
import com.proway.mvvm_auth.services.RefrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BILLS_COLLECTION = "contas"

class BillRepository {

    private val dataBase = Firebase.firestore

    fun fetchBills(callback: (List<Bill>?, String?) -> Unit) {
        dataBase.collection(BILLS_COLLECTION)
            .get()
            .addOnSuccessListener { result ->

                val listOf = arrayListOf<Bill>()
                result.forEach {
                    val conta = Bill.fromData(it)
                    listOf.add(conta)
                }
                callback(listOf, null)

            }
            .addOnFailureListener { exception ->
                callback(null, exception.message)
            }
    }

    fun fetchBill(uid: String, callback: (Bill?, String?) -> Unit) {
        dataBase.collection(BILLS_COLLECTION)
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                val bill = Bill.fromDocument(document)
                callback(bill, null)
            }
            .addOnFailureListener { exception ->
                callback(null, exception.message)
            }
    }

    fun delete(uid: String, callback: (Boolean) -> Unit) {
        dataBase.collection(BILLS_COLLECTION)
            .document(uid)
            .delete()
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }

    fun update(bill: Bill, callback: (Boolean) -> Unit) {
        dataBase.collection(BILLS_COLLECTION)
            .document(bill.uid!!)
            .set(bill)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { exception ->
                callback(false)
            }
    }

    fun addBill(bill: Bill, callback: (Bill?, String?) -> Unit) {
        dataBase.collection(BILLS_COLLECTION)
            .add(bill)
            .addOnSuccessListener {

                val newBill = bill.apply {
                    uid = it.id
                }
                callback(newBill, null)
            }
            .addOnFailureListener { exception ->
                callback(null, exception.message)
            }
    }

    fun getPokemons(callback: (PokeResponse?, String?) -> Unit) {
        val retrofitService = RefrofitService.getPokeService()
        val call = retrofitService.getAll()
        call.enqueue(object : Callback<PokeResponse> {
            override fun onResponse(call: Call<PokeResponse>, response: Response<PokeResponse>) {
                if (response.body() != null) {
                    response.body()?.let { pokeResponse ->
                        callback(pokeResponse, null)
                    }
                } else {
                    callback(null, "Algum outro tipo de erro")
                }
            }

            override fun onFailure(call: Call<PokeResponse>, t: Throwable) {
                callback(null, t.message)
            }
        })
    }


}