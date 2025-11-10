package cz.vican.scratchcard.generic.scratchcard.device

import cz.vican.scratchcard.generic.scratchcard.domain.ScratchCardRepository
import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class InMemoryScratchCardRepository : ScratchCardRepository {
    private val mutableStatus: MutableSharedFlow<ScratchCardStatus> = MutableSharedFlow(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override fun getScratchCardStatus(): ScratchCardStatus = mutableStatus.replayCache.first()

    override fun setScratchCardStatus(status: ScratchCardStatus) {
        mutableStatus.tryEmit(status)
    }

    override fun observeScratchCardStatus(): Flow<ScratchCardStatus> {
        return mutableStatus.asSharedFlow()
    }
}