package cz.vican.architecture.domain

import kotlinx.coroutines.flow.Flow

interface SimpleFlowUseCase<T: Any> {
    fun invoke(): Flow<T>
}