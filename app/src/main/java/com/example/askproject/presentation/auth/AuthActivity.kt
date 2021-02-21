package com.example.askproject.presentation.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.askproject.R
import com.example.askproject.presentation.auth.login.LoginFragment

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            exitTransition = Explode()
        }
        setContentView(R.layout.activity_auth)
        val sharedPref: SharedPreferences =
            getSharedPreferences("IS_WALKTHROUGH", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("IS_WALKTHROUGH", false)
        editor.apply()
    }
}