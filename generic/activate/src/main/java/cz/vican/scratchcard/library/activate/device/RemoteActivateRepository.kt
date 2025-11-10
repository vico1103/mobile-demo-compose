package cz.vican.scratchcard.library.activate.device

import cz.vican.scratchcard.library.activate.api.IActivateApi
import cz.vican.scratchcard.library.activate.data.ActivateResponseBody
import cz.vican.scratchcard.library.activate.domain.ActivateRepository

internal class RemoteActivateRepository(private val api: IActivateApi): ActivateRepository {

    override suspend fun activate(cardId: String): ActivateResponseBody {
       return api.activateCard(cardId)
    }
}