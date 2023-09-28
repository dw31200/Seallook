package com.seallook.androidx.ui.splash

import com.seallook.androidx.databinding.ActivitySplashBinding
import com.seallook.androidx.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(
    ActivitySplashBinding::inflate,
) {
    override fun onCreateAfterBinding() {
        Unit
    }
}
