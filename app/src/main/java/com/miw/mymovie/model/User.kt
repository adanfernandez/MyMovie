package com.miw.mymovie.model;

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User(
    val username: String, val password: String, val name: String = ""
) : Parcelable {
}