package com.example.cinema.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subscription(val img: String, val name: String) : Parcelable
