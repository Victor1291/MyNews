package com.shu.mynews.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProfileViewModelFactory(private val mainViewModel: ProfileViewModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {

            return mainViewModel as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}