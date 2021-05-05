package com.miw.mymovie.model;

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val username: String, val password: String, val name: String = ""
) : Parcelable {
}