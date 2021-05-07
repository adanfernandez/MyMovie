package com.miw.mymovie.model;


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Film(
    val idRemote: Long,
    val title: String?,
    val originalTitle: String?,
    val overview: String?,
    val image: String?,
    val voteCount: Int,
    val voteAverage: Double,
    val seen: Boolean
) : Parcelable {


}