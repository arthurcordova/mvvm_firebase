package com.proway.mvvm_auth.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.repository.BillRepository

class ContentViewModel : ViewModel() {

    private val _bills = MutableLiveData<List<Bill>>()
    val bill: LiveData<List<Bill>> = _bills

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val billsRepository = BillRepository()

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
        Bill(null, name, price).apply {
            billsRepository.addBill(this) { bill, error ->
                if (error != null) {
                    _error.value = error
                } else {
                    fetchContas()
                }
            }
        }
    }
}