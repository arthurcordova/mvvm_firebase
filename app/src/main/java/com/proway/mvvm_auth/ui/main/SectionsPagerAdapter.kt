package com.proway.mvvm_auth.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.view.ContentFragment
import com.proway.mvvm_auth.view.SignInFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {


    private val listOfFrags = listOf<Fragment>(
        ContentFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance(),
        SignInFragment.newInstance()
    )


    override fun getItem(position: Int): Fragment {
        return listOfFrags[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Tab $position"
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return listOfFrags.size
    }
}