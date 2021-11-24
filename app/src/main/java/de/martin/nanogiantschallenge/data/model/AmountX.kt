package de.martin.nanogiantschallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AmountX(
    val unit: String?,
    val value: Double?
) : Parcelable