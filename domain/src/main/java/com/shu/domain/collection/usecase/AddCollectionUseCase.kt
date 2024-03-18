package com.shu.domain.usecase.collections

import com.shu.domain.collection.repository.CollectionRepository
import javax.inject.Inject

class AddCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend fun execute(nameCollection: String, icon: Int) {
        repository.onAdd(nameCollection,icon)
    }
}