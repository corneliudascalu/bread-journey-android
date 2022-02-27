package com.corneliudascalu.bakerjourney.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corneliudascalu.bakerjourney.UiResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LogEntriesViewModel(
    private val repository: ShortLogRepository
) : ViewModel() {
    fun refresh() {
        viewModelScope.launch {
            uiState.emit(UiResult.Loading())
            delay(500)
            uiState.emit(UiResult.Success(UiState(entries = repository.getAll())))
        }
    }

    private val uiState = MutableStateFlow<UiResult<UiState>>(UiResult.Loading())
    val ui: StateFlow<UiResult<UiState>> = uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 3000
        ),
        initialValue = UiResult.Loading()
    )

    data class UiState(
        val entries: List<ShortLogEntry> = emptyList()
    )
}