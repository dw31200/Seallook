package com.seallook.androidx.ui.splash

import androidx.activity.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.ActivitySplashBinding
import com.seallook.androidx.ui.base.BaseActivity
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel, Effect>(
    ActivitySplashBinding::inflate,
) {
    override val viewModel: SplashActivityViewModel by viewModels()

    override fun viewModelVariabledId(): Int = BR.vm

    override fun onCreateAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
