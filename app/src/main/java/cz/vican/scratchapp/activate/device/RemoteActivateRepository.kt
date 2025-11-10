package cz.vican.scratchapp.activate.device

import cz.vican.scratchapp.activate.domain.ActivateRepository
import cz.vican.scratchapp.activate.model.ActivateResponseBody

class RemoteActivateRepository(
): ActivateRepository {
    override suspend fun activate(cardId: String): ActivateResponseBody {
        // Implementation goes here
        TODO("Not yet implemented")
    }
}