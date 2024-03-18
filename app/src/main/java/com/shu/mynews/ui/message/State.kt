package com.shu.mynews.ui.message

sealed class State {

    data object Loading: State()
    data object Success: State()
    data object Error: State()

}

