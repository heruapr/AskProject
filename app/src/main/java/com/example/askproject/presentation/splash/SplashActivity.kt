package com.example.askproject.presentation.splash

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.askproject.R
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar.isShown
        Handler().postDelayed({
            startActivity(
                Intent(this, AuthActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
            finish()
        }, SPLASH_TIME_OUT)
    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 1000 // 1 sec
    }
}