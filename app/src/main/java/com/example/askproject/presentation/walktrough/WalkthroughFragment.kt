package com.example.askproject.presentation.walktrough

import android.app.ActivityOptions
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.askproject.R
import com.example.askproject.presentation.auth.AuthActivity
import com.example.askproject.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_walkthrough.*
import kotlinx.android.synthetic.main.walkthrough_fragment.*

class WalkthroughFragment : Fragment() {

    private var actionCallback: (() -> Unit)? = null

    companion object {
        private const val ARG_INFO = "ARG_INFO"
        private const val ARG_ACTION = "ARG_ACTION"
        private const val ARG_INDEX = "ARG_INDEX"

        fun newInstance(
            info: String,
            action: String? = null,
            index: Int,
            callback: (() -> Unit)? = null
        ) =
            WalkthroughFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_INFO, info)
                    putString(ARG_ACTION, action)
                    putInt(ARG_INDEX, index)
                }
                actionCallback = callback
            }
    }

    private lateinit var viewModel: WalkthroughViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.walkthrough_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WalkthroughViewModel::class.java)
        // TODO: Use the ViewModel
//        when (arguments?.getInt(ARG_INDEX)) {
//            0 -> {
//                view?.setBackgroundColor(resources.getColor(R.color.purple_700))
//                requireActivity().viewPager.setBackgroundColor(resources.getColor(R.color.purple_700))
//            }
//            1 -> {
//                view?.setBackgroundColor(resources.getColor(R.color.purple_500))
//                requireActivity().viewPager.setBackgroundColor(resources.getColor(R.color.purple_500))
//            }
//            2 -> {
//                view?.setBackgroundColor(resources.getColor(R.color.black))
//                requireActivity().viewPager.setBackgroundColor(resources.getColor(R.color.black))
//            }
//        }
        infoTextView.text = arguments?.getString(ARG_INFO)
        actionButton.text = arguments?.getString(ARG_ACTION)
        actionButton.isVisible = arguments?.getString(ARG_ACTION) != null
        actionButton.setOnClickListener {
            startActivity(
                Intent(requireActivity(), AuthActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
            )
            requireActivity().finish()
        }
    }

}