package com.seallook.androidx.ui.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.seallook.androidx.R
import com.seallook.androidx.databinding.ActivityMainGraphBinding
import com.seallook.androidx.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
/* TODO
    1.ViewModel 구현
 */
@AndroidEntryPoint
class MainGraphActivity : BaseActivity<ActivityMainGraphBinding>(
    ActivityMainGraphBinding::inflate,
) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container,
        ) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, arguments ->
            binding.bottomNav.isVisible = arguments?.getBoolean("ShowAppBar", false) == true
        }
    }

    override fun onCreateAfterBinding() {
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
