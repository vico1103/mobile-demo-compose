package cz.vican.scratchapp.activate.domain

import cz.vican.architecture.domain.AbstractSafeFlowUseCase
import kotlinx.coroutines.flow.Flow

class ActivateUseCas(

) : AbstractSafeFlowUseCase<ActivateUseCase.CardId, ActivateUseCase.Response>() {

    override fun execute(argument: CardId): Flow<Result<Response>> {
        return safeExecute(argument) {

        }
    }

    private suspend fun aa(cardId: CardId): Response {

    }


    companion object {
        private const val ANDROID_VALUE = 277028
    }

    data class CardId(val id: String)

    data class Response(val isSuccessful: Boolean)
}