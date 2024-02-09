package com.shu.mynews.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.GetAllPhotoUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FirstViewModel (private val getAllPhotoUseCase: GetAllPhotoUseCase) :
    ViewModel() {

    val allPhoto = getAllPhotoUseCase.execute()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(1000L),
            initialValue = emptyList()
        )
}
