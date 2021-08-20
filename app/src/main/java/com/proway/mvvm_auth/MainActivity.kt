package com.proway.mvvm_auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.proway.mvvm_auth.utils.replaceView
import com.proway.mvvm_auth.view.ContentFragment
import com.proway.mvvm_auth.view.SignInFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onResume() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            replaceView(ContentFragment.newInstance())
        } else {
            replaceView(SignInFragment.newInstance())
        }
        super.onResume()
    }

}