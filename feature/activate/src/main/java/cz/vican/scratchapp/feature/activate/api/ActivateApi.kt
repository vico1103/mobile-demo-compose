package cz.vican.scratchapp.feature.activate.api

import cz.vican.scratchapp.feature.activate.data.ActivateResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivateApi {

    @GET("version")
    suspend fun activateCard(@Query("code") cardCode: String): ActivateResponseBody
}