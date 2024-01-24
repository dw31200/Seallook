package com.seallook.androidx.ui.auth

import androidx.activity.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.ActivityAuthBinding
import com.seallook.androidx.ui.base.BaseActivity
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding, AuthViewModel, Effect>(
    ActivityAuthBinding::inflate,
) {
    override val viewModel: AuthViewModel by viewModels()

    override fun viewModelVariabledId(): Int = BR.vm

    override fun onCreateAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
