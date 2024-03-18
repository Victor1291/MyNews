package com.shu.mynews.ui.visitor.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.shu.domain.news.GetAllNewsUseCase
import com.shu.entity.modelNews.IArticle
import com.shu.mynews.ui.visitor.model.CardItem
import com.shu.mynews.ui.visitor.model.HasStringId
import com.shu.mynews.ui.visitor.model.OneLineItem2
import com.shu.mynews.ui.visitor.model.RecyclerHeader
import com.shu.mynews.ui.visitor.model.TestData
import com.shu.mynews.ui.visitor.model.TwoStringsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.sql.Time
import java.util.Date

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

    private fun newNews(list: List<IArticle>): List<HasStringId> {
    val newList = mutableListOf<HasStringId>()
        list.forEachIndexed { index, iArticle ->
            newList.add(RecyclerHeader(text = "$index. ${iArticle.title}"))
            newList.add(OneLineItem2(left = iArticle.url ?: "", right = "" ))
        }

        list.forEachIndexed { index, art ->
            newList.add(RecyclerHeader(text = "$index. ${art.title}"))
            newList.add(OneLineItem2(left = art.publishedAt ?: "", right = "" ))
            newList.add(CardItem(
                id = art.publishedAt ?: "none",
                image = art.urlToImage ?: "none",
                title = art.title ?: "none",
                description = art.description ?: "none"
            ))
            newList.add(TwoStringsItem(caption = art.description ?: "", details = art.content ?: "" ))
        }
        return newList.toList()
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
                    _news.value = newNews(it.articles)
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