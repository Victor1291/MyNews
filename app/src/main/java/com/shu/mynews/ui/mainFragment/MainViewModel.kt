package com.shu.mynews.ui.mainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.SaveTakenPhotoUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveTakenPhotoUseCase: SaveTakenPhotoUseCase
) : ViewModel() {

    fun saveTakenPhoto (photoData: String, path: String) {
        viewModelScope.launch {
            saveTakenPhotoUseCase.save(photoData = photoData, path = path)
        }
    }
}