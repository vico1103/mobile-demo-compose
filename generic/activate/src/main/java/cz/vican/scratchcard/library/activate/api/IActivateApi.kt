package cz.vican.scratchcard.library.activate.api

import cz.vican.scratchcard.library.activate.data.ActivateResponseBody

interface IActivateApi {

    @GET("activate")
    suspend fun activateCard(@Query("cardNumber") cardNumber: String): ActivateResponseBody
}