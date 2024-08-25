package com.danipl.piggybank.android

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
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
