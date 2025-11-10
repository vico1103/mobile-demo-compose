package cz.vican.architecture.domain

interface SimpleUseCase<T: Any> {
    fun invoke(): T
}