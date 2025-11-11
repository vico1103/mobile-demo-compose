package cz.vican.scratchapp.feature.activate.domain

import cz.vican.scratchapp.feature.activate.data.ActivateResponseBody

internal interface ActivateRepository {
    suspend fun activate(cardCode: String): ActivateResponseBody
}