package com.seallook.androidx.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.서버에서 가져온 CounselorList DB에 저장 및 flow로 가져오기
    2.서버에서 가져온 계정 타입 DB에 저장 및 flow로 가져오기
    필요한 모델: CounselorInfo(id 자동생성으로 변경, documentId 추가), OfficeInfo(id 자동생성으로 변경, documentId 추가)
 */
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, Effect>(
        FragmentHomeBinding::inflate,
    ),
    HomeNavigation {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    getLocation()
                } else -> {
                    getLocation()
                }
            }
        }

    override fun onViewCreatedAfterBinding() {
        getLocation()
        with(binding) {
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            counselorNameTextField.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
                navigate(action)
            }
            reserveCounselingButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
                navigate(action)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToReserveCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReserveCounselingFragment(email)
        navigate(action)
    }

    override fun navigateToReservedClient(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReservedClientListFragment(email)
        navigate(action)
    }

    override fun navigateToReservedCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReservedCounselingListFragment(email)
        navigate(action)
    }

    private fun getLocation() {
        if (checkedPermissions()) {
            Toast.makeText(
                context,
                "권한이 승인되었습니다.",
                Toast.LENGTH_SHORT,
            ).show()
        } else {
            requestPermissionLauncher.launch(
                REQUIRED_PERMISSIONS,
            )
        }
    }

    private fun checkedPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
                .toTypedArray()
    }
}
