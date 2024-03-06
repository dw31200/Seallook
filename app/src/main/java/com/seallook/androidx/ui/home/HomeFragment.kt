package com.seallook.androidx.ui.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import com.seallook.androidx.ui.home.adapter.OfficeListAdapter
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
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    viewModel.getLocation()
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    viewModel.getLocation()
                } else -> Unit
            }
        }

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            officeList.adapter = OfficeListAdapter()
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            showWebSite = this@HomeFragment
        }
        requestPermissionLauncher.launch(
            REQUEST_PERMISSIONS,
        )
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
