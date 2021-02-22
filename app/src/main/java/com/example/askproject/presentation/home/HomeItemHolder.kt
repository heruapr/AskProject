package com.example.askproject.presentation.home

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.askproject.R
import com.example.askproject.domain.home.Content
import com.example.askproject.presentation.extension.inflate
import kotlinx.android.synthetic.main.home_item_holder.view.*

/** 2021-02-21 17:07 Created by: Heru Apr */
class HomeItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var item: Content? = null
    private var size: Int = 0

    private val contentTextView = itemView.contenTextView
    private val nameTextView = itemView.nameTextView
    private val userImageView = itemView.userImageView
    private val likeIcon = itemView.likeButton
    private val commentIcon = itemView.commentButton
    private val repostIcon = itemView.repostButtton
    private val bottom = itemView.bottomCardView
    private val topSeperator = itemView.seperatorTop
    private val bottomSeperator = itemView.seperatorBottom
    private val imageCardView = itemView.imgCardView

    fun onCreate(onItemClicked: (Content) -> Unit, size: Int) {
        itemView.setOnClickListener { item?.run(onItemClicked) }
        this.size = size
    }

    fun onBind(item: Content) {
        this.item = item
        if (item.post == "") {
            likeIcon.visibility = View.GONE
            commentIcon.visibility = View.GONE
            repostIcon.visibility = View.GONE
            userImageView.visibility = View.GONE
            contentTextView.visibility = View.GONE
            bottom.visibility = View.VISIBLE
            nameTextView.visibility = View.GONE
            bottomSeperator.visibility = View.GONE
            imageCardView.visibility = View.GONE
        } else {
            imageCardView.visibility = View.VISIBLE
            nameTextView.visibility = View.VISIBLE
            likeIcon.visibility = View.VISIBLE
            commentIcon.visibility = View.VISIBLE
            repostIcon.visibility = View.VISIBLE
            userImageView.visibility = View.VISIBLE
            bottom.visibility = View.GONE
            contentTextView.text = item.post
            nameTextView.text = item.name
            Glide.with(itemView)
                .load(Uri.parse("https://velvet-sheep.com/wp-content/uploads/2017/04/C3reXRdWcAADCiX.jpg"))
                .into(userImageView)
        }

    }

    companion object {
        fun newInstance(parent: ViewGroup, onItemClicked: (Content) -> Unit, size: Int) =
            HomeItemHolder(parent.inflate(R.layout.home_item_holder)).apply {
                onCreate(onItemClicked, size)
            }
    }
}