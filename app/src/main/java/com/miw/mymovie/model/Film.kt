package com.mymovie.model;

import android.os.Parcel
import android.os.Parcelable

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(idRemote)
        parcel.writeString(title)
        parcel.writeString(originalTitle)
        parcel.writeString(overview)
        parcel.writeString(image)
        parcel.writeInt(voteCount)
        parcel.writeDouble(voteAverage)
        parcel.writeByte(seen)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            val id = parcel.readString() ?: ""
            val idRemote = parcel.readString() ?: ""
            val title = parcel.readString() ?: ""
            val originalTitle = parcel.readString() ?: ""
            val overview = parcel.readString() ?: ""
            val image = parcel.readString() ?: ""
            val voteCount = parcel.readInt() ?: ""
            val voteAverage = parcel.readDouble() ?: ""
            val seen = parcel.readByte() ?: ""


            return Film(id, idRemote, title, originalTitle, overview, image,
                voteCount as Int,
                voteAverage as Double,
                seen as Byte
            )
        }
        override fun newArray(size: Int): Array<Film?> = arrayOfNulls(size)
    }
}