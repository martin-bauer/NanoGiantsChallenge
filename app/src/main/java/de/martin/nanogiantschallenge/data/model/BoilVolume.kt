package de.martin.nanogiantschallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BoilVolume(
    val unit: String?,
    val value: Int?
) : Parcelable