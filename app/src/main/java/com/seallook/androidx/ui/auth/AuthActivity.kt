package com.seallook.androidx.ui.auth

import com.seallook.androidx.databinding.ActivityAuthBinding
import com.seallook.androidx.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(
    ActivityAuthBinding::inflate,
) {
    override fun onCreateAfterBinding() {
        Unit
    }
}
