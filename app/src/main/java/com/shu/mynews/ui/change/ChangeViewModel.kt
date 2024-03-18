package com.shu.mynews.ui.change

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shu.domain.collection.usecase.UpdateMessageUseCase
import com.shu.entity.icollection.IMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeViewModel @Inject constructor(
    private val updateMessageUseCase: UpdateMessageUseCase,
) : ViewModel() {

    fun updateMessage(message: IMessage) {
        viewModelScope.launch(Dispatchers.IO) {
            updateMessageUseCase.execute(message)
        }
    }
}