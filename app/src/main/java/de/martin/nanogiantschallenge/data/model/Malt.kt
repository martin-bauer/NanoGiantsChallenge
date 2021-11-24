package de.martin.nanogiantschallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Malt(
    val amount: AmountX?,
    val name: String?
) : Parcelable