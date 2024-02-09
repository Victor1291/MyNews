package com.shu.mynews.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class FirstViewModelFactory(private val mainViewModel: FirstViewModel) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {

            return mainViewModel as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}
