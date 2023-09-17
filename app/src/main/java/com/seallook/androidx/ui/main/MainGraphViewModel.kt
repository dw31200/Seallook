package com.seallook.androidx.ui.main

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.R
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainGraphViewModel @Inject constructor() : BaseViewModel() {
    private val _toolbarBackEvent = MutableSharedFlow<Unit>()
    val toolbarBackEvent = _toolbarBackEvent.asSharedFlow()

    private val _backToGraphRootEvent = MutableSharedFlow<Unit>()
    val backToGraphRootEvent = _backToGraphRootEvent.asSharedFlow()

    private val _showToolbarBackButton = MutableStateFlow(false)
    val showToolbarBackButton = _showToolbarBackButton.asStateFlow()

    private val _toolbarTitle = MutableStateFlow(R.string.home)
    val toolbarTitle = _toolbarTitle.asStateFlow()

    fun submitToolbarBackEvent() {
        viewModelScope.launch {
            _toolbarBackEvent.emit(Unit)
        }
    }

    fun submitBackToGraphRootEvent() {
        viewModelScope.launch {
            _backToGraphRootEvent.emit(Unit)
        }
    }

    fun showToolbarBackButton(show: Boolean) {
        _showToolbarBackButton.tryEmit(show)
    }

    fun submitToolbarTitle(@StringRes title: Int) {
        _toolbarTitle.tryEmit(title)
    }
}
