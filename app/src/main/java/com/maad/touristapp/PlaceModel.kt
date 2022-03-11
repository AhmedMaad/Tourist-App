package com.maad.touristapp

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
class PlaceModel(
    @DrawableRes val picture: Int,
    val name: String,
    val lat: Double,
    val lon: Double,
    val details: ArrayList<PlaceDetails>
) : Parcelable

@Parcelize
class PlaceDetails(
    val picture: Int,
    val name: String,
    val workingHours: String,
    val ticketPrice: Double,
    @StringRes val details: Int
    //picture - name --> working hours - picture as cover photo (dont add to variables)
// - name (don't add to variables) (make design with coordinate layout)
    //details - tickets
) : Parcelable