package com.example.shoestore.vos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoes (
    val shoeName:String,
    val shoeDescription:String,
    ):Parcelable