package cz.vican.scratchcard.local_storage

import cz.vican.scratchcard.local_storage.AbstractInMemoryStorage.MemoryItem
import kotlinx.coroutines.flow.MutableStateFlow

abstract class AbstractInMemoryStorage<S:MemoryItem> {
    interface MemoryItem

    private val mutableItem: MutableStateFlow<S> = MutableStateFlow(createDefaultMemoryItem())

    val item: S
        get() = mutableItem.value

    protected abstract fun createDefaultMemoryItem():S

    fun setMemoryItem(item: S) {
       mutableItem
    }
}