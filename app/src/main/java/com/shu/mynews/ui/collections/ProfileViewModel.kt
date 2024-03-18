package com.shu.mynews.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.CreateMessageUseCase
import com.shu.domain.GetAllCollectionsUseCase
import com.shu.domain.usecase.collections.AddCollectionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase,
    private val addCollectionUseCase: AddCollectionUseCase,
    private val createMessageUseCase: CreateMessageUseCase
) : ViewModel() {


    val collectionsAll = getAllCollectionsUseCase.execute().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )


    var movieId = 0

    //TODO сделать выбор иконки для коллекции при создании
    fun addCollection(nameCollection: String, icon: Int = 3) {
        viewModelScope.launch(Dispatchers.IO) {
            addCollectionUseCase.execute(nameCollection, icon)
        }
    }

    fun onUpdateBtn() {}

    fun onDeleteBtn() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}