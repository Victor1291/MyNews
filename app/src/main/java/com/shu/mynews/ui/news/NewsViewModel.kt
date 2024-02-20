package com.shu.mynews.ui.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.news.GetAllNewsUseCase
import com.shu.entity.modelNews.IArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


private const val TAG = "MainViewModel"

class NewsViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase
) : ViewModel() {

    // constructor() : this(NewsListRepository())

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _news = MutableStateFlow<List<IArticle>>(emptyList())
    val news = _news.asStateFlow()
    private var newsPage = 1
    private var country = "us"
    private var category = "technology"

    init {
        loadNews()
    }

    private fun loadNews() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true
                getAllNewsUseCase.execute(
                    countryCode = country,
                    pageNumber = newsPage,
                    category = category
                )
            }.fold(
                onSuccess = { _news.value = it.articles },
                onFailure = {
                    Log.d("NewsListViewModel", it.message ?: "")
                }
            )
            _isLoading.value = false
        }
    }

    fun refresh() {
        loadNews()
    }

    fun changeCategory(text: String) {
        category = text
    }

    init {
        Log.d(TAG, "init: ")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }

    fun changePage() {
        newsPage++
        loadNews()
    }

    fun getPage(): Int {
        return newsPage
    }

    fun resetPage() {
        newsPage = 1
        loadNews()
    }

}

/*

GET https://newsapi.org/v2/everything?q=Apple&from=2023-05-18&sortBy=popularity&apiKey=API_KEY
Example request
curl https://newsapi.org/v2/everything -G \
    -d q=Apple \
    -d from=2023-05-18 \
    -d sortBy=popularity \
    -d apiKey=e2d3130fe991405ea6d78eff5d58a101

    ar de en es fr he it nl no pt ru sv ud zh.

    @Query("from") data: String = "2023-05-18",
        @Query("sortBy") sortBy: String = "popularity",
category
The category you want to get headlines for. Possible options:
 business
 entertainment
 general
 health
 science
 sports
 technology.
  Note: you can't mix this param with the sources param.
 */