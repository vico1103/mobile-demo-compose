package cz.vican.scratchcard.library.activate.domain

import cz.vican.scratchcard.library.activate.data.ActivateResponseBody

internal interface ActivateRepository {
    suspend fun activate(cardId:String): ActivateResponseBody
}