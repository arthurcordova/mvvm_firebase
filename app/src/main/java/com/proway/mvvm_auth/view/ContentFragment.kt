package com.proway.mvvm_auth.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.adapter.ContasAdapter
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.view_model.ContentViewModel

class ContentFragment : Fragment(R.layout.content_fragment) {

    companion object {
        fun newInstance() = ContentFragment()
    }

    private lateinit var viewModel: ContentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val adapter = ContasAdapter()

    val observerContas = Observer<List<Bill>> {
        adapter.refresh(it)
        swipeRefreshLayout.isRefreshing = false
    }

    val observerError = Observer<String> {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContentViewModel::class.java)

        swipeRefreshLayout = view.findViewById(R.id.swipeContainer)
        recyclerView = view.findViewById(R.id.contasRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.error.observe(viewLifecycleOwner, observerError)
        viewModel.bill.observe(viewLifecycleOwner, observerContas)


        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            val inputName = view.findViewById<EditText>(R.id.inputNameEditText)
            val inputPrice = view.findViewById<EditText>(R.id.inputPriceEditText)
            if (inputName.text.toString().isNotEmpty() && inputPrice.text.toString().isNotEmpty()) {
                viewModel.addBill(
                    inputName.text.toString(),
                    inputPrice.text.toString().toDoubleOrNull()
                )
            }
        }

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
        loadData()

    }

    fun loadData() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.fetchContas()
    }

}