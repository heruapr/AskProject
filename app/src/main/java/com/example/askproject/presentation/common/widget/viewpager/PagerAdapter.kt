package com.example.askproject.presentation.common.widget.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.askproject.presentation.walktrough.WalkthroughFragment

/** 2021-02-21 12:47 Created by: Heru Apr */
class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val pages = listOf(
        WalkthroughFragment.newInstance("Haloo"),
        WalkthroughFragment.newInstance("Haloo duaa"),
        WalkthroughFragment.newInstance("Haloo tigaaa", "Mulaii!")
    )

    override fun getCount(): Int {
        return pages.size
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }
}