package com.shu.mynews.ui.change

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shu.mynews.ui.gallery.FirstViewModel
import java.lang.IllegalArgumentException

class ChangeViewModelFactory (private val mainViewModel: ChangeViewModel) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangeViewModel::class.java)) {

            return mainViewModel as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}