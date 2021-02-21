package com.example.askproject.presentation.auth.login

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.Explode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.askproject.R
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
//        loginButton.startAnimation(shakeAnim)
        loginButton.setOnClickListener {
            startActivity(
                Intent(requireActivity(), MainActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
            requireActivity().finish()
        }
        registButton.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }
}