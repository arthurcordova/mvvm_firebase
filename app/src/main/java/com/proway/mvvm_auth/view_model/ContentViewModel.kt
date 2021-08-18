package com.proway.mvvm_auth.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.repository.AuthenticationRepository
import com.proway.mvvm_auth.repository.BillRepository

class ContentViewModel : ViewModel() {

    private val _bills = MutableLiveData<List<Bill>>()
    val bill: LiveData<List<Bill>> = _bills

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _isSignedIn = MutableLiveData<Boolean>(true)
    val isSignedIn: LiveData<Boolean> = _isSignedIn

    private val _user = MutableLiveData<FirebaseUser>()
    val user: LiveData<FirebaseUser> = _user

    private val billsRepository = BillRepository()
    private val authenticationRepository = AuthenticationRepository()

    fun fetchContas() {
        billsRepository.fetchBills { bills, error ->
            if (error != null) {
                _error.value = error
            } else {
                _bills.value = bills
            }
        }
    }

    fun addBill(name: String, price: Double?) {
        val newBill = Bill(null, name, price)
        billsRepository.addBill(newBill) { _, error ->
            if (error != null) {
                _error.value = error
            } else {
                fetchContas()
            }
        }
    }

    fun fetchCurrentUser() {
        authenticationRepository.currentUser()?.apply {
            _user.value = this
        }
    }

    fun signOut() {
        authenticationRepository.signOut()
        _isSignedIn.value = false
    }
}