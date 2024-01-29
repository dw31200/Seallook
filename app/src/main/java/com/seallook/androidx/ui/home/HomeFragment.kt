package com.seallook.androidx.ui.home

import android.content.Intent
import android.net.Uri
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

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            showWebSite = this@HomeFragment
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
}
