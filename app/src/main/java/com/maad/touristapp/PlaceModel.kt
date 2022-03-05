package com.maad.touristapp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
class PlaceModel(
    @DrawableRes val picture: Int,
    val name: String,
    val lat: Double,
    val lon: Double,

    ) : Parcelable