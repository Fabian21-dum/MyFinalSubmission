package com.example.myfinalsubmission
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sings(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
