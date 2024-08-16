package com.danipl.piggybank.android

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(
        MainState(
            isBottomNavigationBarVisible = true,
        ),
    )
    val state = _state.asStateFlow()

    fun setBottomNavigationBarVisibility(visible: Boolean) {
        _state.update {
            it.copy(
                isBottomNavigationBarVisible = visible,
            )
        }
    }
}

data class MainState(
    val isBottomNavigationBarVisible: Boolean,
)
