package com.proway.mvvm_auth.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.proway.mvvm_auth.repository.AuthenticationRepository

class MainViewModel : ViewModel() {

    private val _user = MutableLiveData<FirebaseUser?>()
    var user: LiveData<FirebaseUser?> = _user

    private val repository = AuthenticationRepository()

    fun createNewAccount(email: String, password: String) {
        repository.createAccountWithEmailPassword(email, password) {
            _user.value = it
        }
    }

}