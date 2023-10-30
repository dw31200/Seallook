package com.seallook.androidx.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seallook.androidx.base.Effect
import com.seallook.androidx.base.Event

abstract class BaseViewModel<E : Effect> : ViewModel() {

    private val _effect = MutableLiveData<Event<E>>()
    val effect: LiveData<Event<E>>
        get() = _effect

    protected fun setEffect(effect: E) {
        _effect.value = Event(effect)
    }
}
