package com.seallook.androidx.ui.reserve.counseling

import androidx.lifecycle.SavedStateHandle
import com.seallook.androidx.base.Effect
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReserveCounselingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<Effect>() {
    var email = savedStateHandle.get<String>("email")
}
