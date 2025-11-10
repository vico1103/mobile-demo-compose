package cz.vican.scratchcard.generic.scratchcard.device

import cz.vican.scratchcard.generic.scratchcard.domain.ScratchCardRepository
import cz.vican.scratchcard.generic.scratchcard.model.ScratchCardStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class InMemoryScratchCardRepository : ScratchCardRepository {
    private val mutableStatus: MutableStateFlow<ScratchCardStatus> = MutableStateFlow(
        ScratchCardStatus(ScratchCardStatus.Status.UNSCRATCHED)
    )

    override fun getScratchCardStatus(): ScratchCardStatus = mutableStatus.replayCache.first()

    override fun setScratchCardStatus(status: ScratchCardStatus) {
        mutableStatus.tryEmit(status)
    }

    override fun observeScratchCardStatus(): Flow<ScratchCardStatus> {
        return mutableStatus.asSharedFlow()
    }
}