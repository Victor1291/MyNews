package com.shu.mynews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shu.mynews.ui.gallery.FirstViewModel
import java.lang.IllegalArgumentException


//2 нужно реализовать метод create
//Если мы будем неправильно использовать нашу фабрику , мы кинем ошибку .
//добавим эту фабрику в делегат
//

class NewsViewModelFactory(private val mainViewModel: NewsViewModel) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {

            return mainViewModel as T

        }
        throw IllegalArgumentException("Unknown class name")
    }
}