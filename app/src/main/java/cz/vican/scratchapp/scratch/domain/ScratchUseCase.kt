package cz.vican.scratchapp.scratch.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ScratchUseCase {

    @OptIn(ExperimentalUuidApi::class)
    fun invoke(): Flow<Uuid> = flow {
        delay(DEFAULT_SCRATCH_DURATION)
        emit(Uuid.random())
    }

    companion object {
        private val DEFAULT_SCRATCH_DURATION: Duration = 2.seconds
    }
}