package com.seallook.androidx.ui

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.seallook.androidx.R
import com.seallook.androidx.databinding.ActivityMainBinding
import com.seallook.androidx.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate,
) {
    override fun onCreateAfterBinding() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navHostFragment.findNavController().also {
            binding.bottomNavigationView.setupWithNavController(it)
        }
    }
}
