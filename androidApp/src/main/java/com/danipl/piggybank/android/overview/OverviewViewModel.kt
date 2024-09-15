package com.danipl.piggybank.android.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danipl.piggybank.android.domain.Asset
import com.danipl.piggybank.android.domain.usecases.GetAllAssetsUseCase
import com.danipl.piggybank.android.domain.usecases.GetTotalWorthUseCase
import com.danipl.piggybank.android.domain.usecases.RemoveAssetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val removeAssetUseCase: RemoveAssetUseCase,
    private val getAllAssetsUseCase: GetAllAssetsUseCase,
    private val getTotalWorthUseCase: GetTotalWorthUseCase,
) : ViewModel() {

    private var initialListOfAssets = mutableListOf<Asset>()

    init {
        viewModelScope.launch {
            initialListOfAssets.addAll(getAllAssetsUseCase.invoke())
        }
    }

    private val _state = MutableStateFlow(
        OverviewState(
            totalWorthAmount = getTotalWorthUseCase(initialListOfAssets),
            assets = initialListOfAssets,
        ),
    )

    val state = _state.asStateFlow()

    fun removeAssetUseCase() {
    }

    fun refreshAssets() {
        viewModelScope.launch {
            _state.update {
                OverviewState(
                    assets = getAllAssetsUseCase.invoke(),
                    totalWorthAmount = getTotalWorthUseCase(state.value.assets),
                )
            }
        }
    }
}

data class OverviewState(
    val totalWorthAmount: String,
    val assets: List<Asset>,
)
