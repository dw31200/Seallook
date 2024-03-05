package com.seallook.androidx.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, Effect>(
        FragmentHomeBinding::inflate,
    ),
    HomeNavigation,
    HomeShowWebSite {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) {
            getCurrentLocation()
        }

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            showWebSite = this@HomeFragment
        }
        getCurrentLocation()
    }

    private fun getCurrentLocation() {
        if (checkPermissions()) {
            viewModel.testGetLocation()
        } else {
            requestPermissionLauncher.launch(
                REQUEST_PERMISSIONS,
            )
        }
    }

    private fun checkPermissions(): Boolean {
        return REQUEST_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToReserveCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReserveCounselingFragment(email)
        navigate(action)
    }

    override fun navigateToSearchCounselor() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
        navigate(action)
    }

    override fun show(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }

    companion object {
        val REQUEST_PERMISSIONS =
            mutableListOf(Manifest.permission.ACCESS_FINE_LOCATION)
                .apply {
                    add(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
                .toTypedArray()
    }
}
