package com.example.askproject.presentation.home

import android.app.ActivityOptions
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askproject.R
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        logoutButton.setOnClickListener {
            startActivity(
                Intent(requireActivity(), AuthActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
            requireActivity().finish()
        }
    }

}