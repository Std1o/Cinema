package com.example.cinema.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("description") val description: String,
    @SerializedName("shortDescription") val shortDescription: String?,
    @SerializedName("year") val year: Int,
    @SerializedName("ageRating") val ageRating: Int,
    @SerializedName("poster") val poster: Poster,
    @SerializedName("rating") val rating: Rating
) : Parcelable