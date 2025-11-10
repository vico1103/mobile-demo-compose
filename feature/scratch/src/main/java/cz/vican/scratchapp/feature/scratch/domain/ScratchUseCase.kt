package cz.vican.scratchapp.feature.scratch.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal class ScratchUseCase {

    @OptIn(ExperimentalUuidApi::class)
    fun scratch() = flow {
        delay(DELAY_SECOND.seconds)
        emit(Uuid.random().toString())
    }

    companion object {
        private const val DELAY_SECOND = 2
    }
}