package com.bpst.themoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("original_title") val title: String,
    val overview: String,
    @SerializedName("poster_path") val poster: String,
    @SerializedName("tagline") val subtitle: String,
    val id: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    )

    fun isValid(): Boolean {
        if (title.isNullOrEmpty() || overview.isNullOrEmpty() || poster.isNullOrEmpty()) return false
        return true
    }

    fun getPosterSmall() = "https://image.tmdb.org/t/p/w300/$poster"
    fun getPosterOriginal() = "https://image.tmdb.org/t/p/original/$poster"
    override fun equals(other: Any?): Boolean {
        if (other is MovieModel)
            return id == other.id
        return false
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(poster)
        parcel.writeString(subtitle)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}
