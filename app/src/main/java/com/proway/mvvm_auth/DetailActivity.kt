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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.toolBar.apply {
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.getStringExtra("bill_id")?.let { uid ->
            binding.viewPager.adapter = SectionsPagerAdapter(uid, supportFragmentManager)
            binding.tabs.setupWithViewPager(binding.viewPager)
        }
    }

}