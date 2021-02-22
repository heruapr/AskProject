package com.example.askproject.presentation.home

import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.askproject.R
import com.example.askproject.domain.home.Content
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private val homeAdapter = HomeAdapter {
//            val direction = KhazanahListFragmentDirections.actionToKhazanahDetailFragment(it)
//            findNavController().navigate(direction)
    }
    private var list = mutableListOf<Content>()


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
        list.add(Content(0, "Heru Apriadi", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(1, "Ricky Irandi", "aku juga nihh..", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", ""))
        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa LAST", ""))
        list.add(Content(2, "", "", ""))
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }
        homeAdapter.addItems(list)
//        logoutButton.setOnClickListener {
//            startActivity(
//                Intent(requireActivity(), AuthActivity::class.java),
//                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
//            )
//            requireActivity().finish()
//        }
    }

}