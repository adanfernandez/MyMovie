package com.mymovie.model;

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Film(
    val id: String,
    val idRemote: String,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    val image: String?,
    val voteCount: Int,
    val voteAverage: Double,
    val seen: Byte
) : Parcelable {


}