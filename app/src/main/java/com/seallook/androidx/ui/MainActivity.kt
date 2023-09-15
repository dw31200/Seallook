package com.seallook.androidx.ui

import com.seallook.androidx.databinding.ActivityMainBinding
import com.seallook.androidx.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate,
) {
    override fun onCreateAfterBinding() = Unit
}
