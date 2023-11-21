package com.seallook.androidx.ui.base

import androidx.lifecycle.ViewModel
import com.seallook.androidx.base.Effect
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<E : Effect> : ViewModel() {
    private val _effect = MutableStateFlow<E?>(null)
    val effect: StateFlow<E?> = _effect.asStateFlow()

    protected fun setEffect(effect: E) {
        _effect.value = effect
    }

    fun clearEffect() {
        _effect.value = null
    }
}
