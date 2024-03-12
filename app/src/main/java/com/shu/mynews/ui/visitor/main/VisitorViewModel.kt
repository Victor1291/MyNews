package com.shu.mynews.ui.visitor.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shu.domain.news.GetAllNewsUseCase
import com.shu.mynews.ui.visitor.model.CardItem
import com.shu.mynews.ui.visitor.model.HasStringId
import com.shu.mynews.ui.visitor.model.OneLineItem2
import com.shu.mynews.ui.visitor.model.TestData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

class VisitorViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase
) : ViewModel() {

    private var newsPage = 1
    private var country = "us"
    private var category = "technology"

    private val items = flow {
        emitAll(TestData.cardFlow)
        emitAll(TestData.twoLinesFlow)
        emitAll(TestData.oneLineFlow)
    }

    fun getItems() = items.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _news = MutableStateFlow<List<HasStringId>>(emptyList())
    val news = _news.asStateFlow()

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
                onSuccess = {
                    _news.value =
                        it.articles.map { article ->
                            CardItem(
                                id = article.publishedAt ?: "none",
                                image = article.urlToImage ?: "none",
                                title = article.title ?: "none",
                                description = article.content ?: "none"
                            )

                        }
                },
                onFailure = {
                    Log.d("NewsListViewModel", it.message ?: "")
                }
            )
            _isLoading.value = false
        }
    }


}

class VisitorViewModelFactory(
    private val visitorViewModel: VisitorViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VisitorViewModel::class.java)) {
            return visitorViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}