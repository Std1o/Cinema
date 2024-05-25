package com.example.cinema.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(@SerializedName("kp") val rating: Double) : Parcelable
