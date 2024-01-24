package com.seallook.androidx.ui.main

import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainGraphViewModel @Inject constructor() : BaseViewModel<Effect>()
