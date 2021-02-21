package com.example.askproject.presentation.auth.register

import android.app.ActivityOptions
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.transition.Explode
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.askproject.R
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
        registButton.setOnClickListener {
            startActivity(
                Intent(requireActivity(), MainActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
            requireActivity().finish()
        }
    }

}