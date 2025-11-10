package cz.vican.scratchapp.feature.activate.device

import cz.vican.scratchapp.feature.activate.api.ActivateApi
import cz.vican.scratchapp.feature.activate.data.ActivateResponseBody
import cz.vican.scratchapp.feature.activate.domain.ActivateRepository

internal class RemoteActivateRepository(private val api: ActivateApi) : ActivateRepository {

    override suspend fun activate(cardCode: String): ActivateResponseBody {
        return api.activateCard(cardCode)
    }
}