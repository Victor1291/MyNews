package com.shu.mynews.ui.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.CreateMessageUseCase
import com.shu.domain.GetAllMessageUseCase
import com.shu.entity.icollection.IMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MessageViewModel(
    private val getAllMessageUseCase: GetAllMessageUseCase,
    private val createMessageUseCase: CreateMessageUseCase
) : ViewModel() {
    // TODO: Implement the ViewModel

    val wordSearch = MutableStateFlow("")

    private val _state = MutableStateFlow<State>(State.Success)
    val state = _state.asStateFlow()

    private val _messages = MutableStateFlow<List<IMessage>>(emptyList())
    private val messages = _messages.asStateFlow()

    val listMessages = combine(messages, wordSearch) { messages, wordSearch ->
        if (wordSearch.isNotEmpty())
            messages.filter { message ->
                message.message.contains(wordSearch,true) ?: false
            }
        else messages
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = messages.value
    )

   fun loadList(collectionId: Int) {

       viewModelScope.launch(Dispatchers.IO) {
           load(collectionId)
       }
   }

    private suspend fun load(collectionId: Int) {
        kotlin.runCatching {
            _state.value = State.Loading
            getAllMessageUseCase.execute(collectionId)
        }.fold(
            onSuccess = {
                _messages.value = it
            },
            onFailure = {
                _state.value = State.Error
            }
        )
    }


    fun createMessage(message: IMessage) {
        viewModelScope.launch(Dispatchers.IO) {
            createMessageUseCase.execute(message)
            load(message.collectionId)
        }
    }

}