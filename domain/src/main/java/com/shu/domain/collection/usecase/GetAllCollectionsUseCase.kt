package com.shu.domain

import com.shu.entity.icollection.Collections
import com.shu.domain.collection.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository
) {

    fun execute(): Flow<List<Collections>> {
        return repository.getCollection()
    }

  /*  fun execute(filmId: Int): Flow<List<Collections>> {
        return repository.getCollection(filmId)
    }*/


}