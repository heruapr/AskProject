package com.example.askproject.domain.home

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/** 2021-02-21 17:04 Created by: Heru Apr */
@Parcelize
data class Content(
    val id: Int,
    val name: String,
    val post: String,
    val img: String
) : Parcelable {
    data class Post(
        val id: Int,
        val post: String
    )
}