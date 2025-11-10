package cz.vican.scratchapp.feature.activate.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ActivateResponseBody(val android: Int)