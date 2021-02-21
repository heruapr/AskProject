package com.example.askproject.presentation.common.widget.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/** 2021-02-21 12:28 Created by: Heru Apr */
class BaseFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    internal var fragments: List<Fragment> = emptyList()

    override fun createFragment(position: Int) = fragments[position]

    override fun getItemCount(): Int = fragments.size

}