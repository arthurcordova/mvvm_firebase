package com.proway.mvvm_auth.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.proway.mvvm_auth.MainActivity
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.ui.main.ViewType
import com.proway.mvvm_auth.view_model.DetailViewModel

class DetailsFragment(private val uid: String, private val viewType: ViewType) :
    Fragment(R.layout.details_fragment) {


    private lateinit var viewModel: DetailViewModel
    private lateinit var root: View
    private val observerBill = Observer<Bill> { bill ->
        if (viewType == ViewType.EDIT) bindEdit(bill) else bindDetail(bill)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root = view
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.bill.observe(viewLifecycleOwner, observerBill)
        viewModel.actionDelete.observe(viewLifecycleOwner, Observer {
            requireActivity().finish()
        })
        viewModel.actionEdit.observe(viewLifecycleOwner, Observer {
            requireActivity().finish()
        })
        viewModel.fetchDetails(uid)
        viewModel.getPokemons()

        view.findViewById<View>(R.id.containerDetails).apply {
            visibility = if (viewType == ViewType.DETAILS) VISIBLE else GONE
        }
        view.findViewById<View>(R.id.containerEdit).apply {
            visibility = if (viewType == ViewType.EDIT) VISIBLE else GONE
        }
    }

    fun bindEdit(bill: Bill) {
        val inputName = root.findViewById<EditText>(R.id.nameEditText).apply {
            setText(bill.name)
        }
        val inputPrice = root.findViewById<EditText>(R.id.priceEditText).apply {
            setText(bill.price?.toString())
        }
        root.findViewById<View>(R.id.buttonDelete).apply {
            setOnClickListener {
                viewModel.delete(uid)
            }
        }
        root.findViewById<View>(R.id.buttonSave).apply {
            setOnClickListener {
                viewModel.update(
                    inputName.text.toString(),
                    inputPrice.text.toString().toDoubleOrNull(),
                    uid
                )
            }
        }
    }

    fun bindDetail(bill: Bill) {
        root.findViewById<TextView>(R.id.nameTextView).apply {
            text = bill.name
        }
        root.findViewById<TextView>(R.id.priceTextView).apply {
            text = bill.price?.toString()
        }
    }


}