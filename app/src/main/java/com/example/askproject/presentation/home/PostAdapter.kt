package com.example.askproject.presentation.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.askproject.domain.home.Content

/** 2021-02-21 17:07 Created by: Heru Apr */
class PostAdapter(
    private val onItemClicked: (Content) -> Unit,
    private val onImageClicked: (Content) -> Unit
) : RecyclerView.Adapter<PostItemHolder>() {

    private var contentItems = mutableListOf<Content>()

    fun addItems(contentItems: List<Content>) {
        this.contentItems.clear()
        this.contentItems.addAll(contentItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemHolder {
        return PostItemHolder.newInstance(parent, onItemClicked, onImageClicked, itemCount)
    }

    fun removeItem(position: Int) {
        notifyItemRemoved(position)
    }

    fun insertItem() {
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: PostItemHolder, position: Int) {
        holder.onBind(contentItems[position])

    }

    override fun getItemCount(): Int {
        return contentItems.size
    }

}