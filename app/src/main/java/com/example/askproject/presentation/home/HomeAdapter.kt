package com.example.askproject.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.askproject.domain.home.Content

/** 2021-02-21 17:07 Created by: Heru Apr */
class HomeAdapter(private val onItemClicked: (Content) -> Unit): RecyclerView.Adapter<HomeItemHolder>() {

    private var contentItems = mutableListOf<Content>()

    fun addItems(contentItems: List<Content>) {
        this.contentItems.clear()
        this.contentItems.addAll(contentItems)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemHolder {
        return HomeItemHolder.newInstance(parent, onItemClicked, itemCount)
    }

    override fun onBindViewHolder(holder: HomeItemHolder, position: Int) {
        holder.onBind(contentItems[position])
    }

    override fun getItemCount(): Int {
       return contentItems.size
    }

}