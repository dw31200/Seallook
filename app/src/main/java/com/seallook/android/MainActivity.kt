package com.seallook.android

import com.seallook.android.databinding.ActivityMainBinding
import com.seallook.android.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate,
) {
    override fun onCreateAfterBinding() = Unit
}
