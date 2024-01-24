package com.seallook.androidx.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.ActivityMainGraphBinding
import com.seallook.androidx.ui.base.BaseActivity
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainGraphActivity : BaseActivity<ActivityMainGraphBinding, MainGraphViewModel, Effect>(
    ActivityMainGraphBinding::inflate,
) {
    private lateinit var navController: NavController

    override val viewModel: MainGraphViewModel by viewModels()

    override fun viewModelVariabledId(): Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container,
        ) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.bottomNav.isVisible = arguments?.getBoolean("ShowAppBar", false) == true
        }
    }

    override fun onCreateAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
