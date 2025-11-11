package cz.vican.scratchapp.feature.activate.domain

import kotlinx.coroutines.flow.flow

class ActivateUseCase internal constructor(private val repository: ActivateRepository) {
    fun invoke(cardId: String) = flow {
        try {
            emit(Result.success(Data(isValid(repository.activate(cardId).android.toInt()))))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    private fun isValid(activationCode: Int) = activationCode > MINIMUM

    data class Data(val isActivated: Boolean)

    companion object {
        private const val MINIMUM = 277028
    }
}