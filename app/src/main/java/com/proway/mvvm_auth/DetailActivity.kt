package com.proway.mvvm_auth

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.proway.mvvm_auth.ui.main.SectionsPagerAdapter
import com.proway.mvvm_auth.databinding.ActivityDetailBinding
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.repository.BillRepository
import com.proway.mvvm_auth.view_model.ContentViewModel
import com.proway.mvvm_auth.view_model.DetailViewModel

class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    private val observerBill = Observer<Bill> { bill ->
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, bill)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)


        binding.toolBar.apply {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        viewModel.bill.observe(this, observerBill)
        intent.getStringExtra("bill_id")?.let { uid ->
            viewModel.fetchDetails(uid)
        }


//        val fab: FloatingActionButton = binding.fab
//fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

}