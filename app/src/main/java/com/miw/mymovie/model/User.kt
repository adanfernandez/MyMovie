package com.mymovie.model;

import android.os.Parcel
import android.os.Parcelable

data class User (
    val username: String, var password: String, val repeatPassword: String
) : Parcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(repeatPassword)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            val username = parcel.readString() ?: ""
            val password = parcel.readString() ?: ""
            val repeatPassword = parcel.readString() ?: ""
            return User(username, password, repeatPassword)
        }
        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}