package com.shu.mynews.ui.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class MessageViewModelFactory(private val mainViewModel: MessageViewModel) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {

            return mainViewModel as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}
