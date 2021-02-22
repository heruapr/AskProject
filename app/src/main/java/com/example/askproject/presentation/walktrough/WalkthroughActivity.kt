package com.example.askproject.presentation.walktrough

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.example.askproject.R
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.common.widget.viewpager.BaseFragmentStateAdapter
import com.example.askproject.presentation.common.widget.viewpager.PagerAdapter
import kotlinx.android.synthetic.main.activity_walkthrough.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class WalkthroughActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)
        supportActionBar?.hide()
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        viewPager.adapter = PagerAdapter(supportFragmentManager)
    }

}