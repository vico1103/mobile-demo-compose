package cz.vican.scratchapp.activate.domain

import cz.vican.scratchapp.activate.model.ActivateResponseBody

interface ActivateRepository {

    suspend fun activate(cardId: String): ActivateResponseBody
}