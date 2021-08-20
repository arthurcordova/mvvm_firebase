package com.proway.mvvm_auth.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.repository.BillRepository

class DetailViewModel : ViewModel() {

    private val _bill = MutableLiveData<Bill>()
    val bill: LiveData<Bill> = _bill

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _actionDelete = MutableLiveData<Boolean>()
    val actionDelete: LiveData<Boolean> = _actionDelete

    private val _actionEdit = MutableLiveData<Boolean>()
    val actionEdit: LiveData<Boolean> = _actionEdit

    private val repository = BillRepository()

    fun fetchDetails(uid: String) {
        repository.fetchBill(uid) { bill, error ->
            if (error != null) {
                _error.value = error
            } else {
                _bill.value = bill
            }
        }
    }

    fun delete(uid: String) {
        repository.delete(uid) { success ->
            _actionDelete.value = success
        }
    }

    fun update(name: String, price: Double?, uid: String) {
        Bill(uid, name, price).let {
            repository.update(it) { success ->
                _actionEdit.value = success
            }
        }
    }

    fun getPokemons() {
        repository.getPokemons() { pokeResponse, error ->
            print("")
        }


    }


}