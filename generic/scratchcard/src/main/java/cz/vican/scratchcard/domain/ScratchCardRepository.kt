package cz.vican.scratchcard.domain

import cz.vican.scratchcard.model.ScratchCardStatus
import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {
    fun getScratchCardStatus(): ScratchCardStatus
    fun setScratchCardStatus(status: ScratchCardStatus)

    fun observeScratchCardStatus(): Flow<ScratchCardStatus>
}