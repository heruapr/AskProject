package com.example.askproject.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.askproject.R
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val navController by lazy { (mainContainer as NavHostFragment).findNavController() }
    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.chatFragment,
                R.id.notificationFragment,
                R.id.profileFragment
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var shakeAnim = AnimationUtils.loadAnimation(this, R.anim.glow)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            exitTransition = Explode()
        }
        setContentView(R.layout.activity_main)
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<HomeFragment>(R.id.mainContainer)
//            }
//        }

        //setup navbar bottom
//        bottomBar.background = resources.getDrawable(R.drawable.round_nav_bar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {

                }
                R.id.chatFragment -> {

                }
                R.id.notificationFragment -> {

                }
                R.id.profileFragment -> {

                }
                else -> {

                }
            }
        }
        bottomNavigation.setOnApplyWindowInsetsListener { view, insets ->
            view.updatePadding(bottom = 1)
            insets
        }
        fab.setOnClickListener {
            fab.startAnimation(shakeAnim)
        }
    }
}