package com.shu.mynews.ui.visitor.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shu.mynews.ui.home.HomeViewModel
import com.shu.mynews.ui.visitor.model.TestData
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import java.lang.IllegalArgumentException

class VisitorViewModel : ViewModel() {

    private val items = flow {
        emitAll(TestData.cardFlow)
        emitAll(TestData.twoLinesFlow)
        emitAll(TestData.oneLineFlow)
    }

    fun getItems() = items.shareIn(viewModelScope, SharingStarted.Lazily, 1)

}

class VisitorViewModelFactory(
    private val visitorViewModel: VisitorViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return visitorViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}