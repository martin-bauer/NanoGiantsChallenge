package de.martin.nanogiantschallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MashTemp(
    val duration: Int?,
    val temp: TempX?
):Parcelable