package com.proway.mvvm_auth.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.proway.mvvm_auth.R
import com.proway.mvvm_auth.model.Bill
import com.proway.mvvm_auth.view.ContentFragment
import com.proway.mvvm_auth.view.DetailsFragment
import com.proway.mvvm_auth.view.SignInFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(
    private val uid: String,
    fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) DetailsFragment(uid, ViewType.DETAILS) else DetailsFragment(
            uid,
            ViewType.EDIT
        )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Details" else "Edit"
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}

enum class ViewType {
    DETAILS,
    EDIT
}
