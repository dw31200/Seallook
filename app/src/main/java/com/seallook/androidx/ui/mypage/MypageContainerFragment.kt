package com.seallook.androidx.ui.mypage

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentMypageContainerBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.ext.findRootNavController
import com.seallook.androidx.ui.main.MainGraphViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MypageContainerFragment : BaseFragment<FragmentMypageContainerBinding, MainGraphViewModel>(
    FragmentMypageContainerBinding::inflate,
) {
    override val viewModel: MainGraphViewModel by viewModels(ownerProducer = {
        findRootNavController().getBackStackEntry(R.id.mainFragment)
    })

    override fun viewModelVariableId(): Int = BR.vm

    private val navController by lazy {
        binding.mypageNavigationContainer.getFragment<NavHostFragment>().navController
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navController.popBackStack()
        }
    }

    override fun onViewCreatedAfterBinding() {
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, onBackPressedCallback)
        addDestinationChangeListener()

        viewModel.toolbarBackEvent
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { navController.popBackStack() }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.backToGraphRootEvent
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { navController.popBackStack(navController.graph.findStartDestination().id, false) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun addDestinationChangeListener() {
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?,
                ) {
                    if (_binding == null) {
                        controller.removeOnDestinationChangedListener(this)
                        return
                    }

                    destination.hierarchy.forEach {
                        submitToolbarTitle(it.id)
                        configureBackNavigation(it.id)
                    }
                }
            },
        )
    }

    private fun submitToolbarTitle(destinationId: Int) = when (destinationId) {
        R.id.mypageFragment -> R.string.mypage
        R.id.updateCounselorBasicInfoFragment -> R.string.update_counselor_basicinfo
        R.id.updateCounselingTypeFragment -> R.string.update_counseling_type
        R.id.updateOfficeFragment -> R.string.update_office
        else -> null
    }?.let(viewModel::submitToolbarTitle)

    private fun configureBackNavigation(destinationId: Int) = when (destinationId) {
        navController.graph.findStartDestination().id -> {
            onBackPressedCallback.isEnabled = false
            viewModel.showToolbarBackButton(false)
        }

        navController.graph.id -> Unit
        else -> {
            onBackPressedCallback.isEnabled = true
            viewModel.showToolbarBackButton(true)
        }
    }
}
