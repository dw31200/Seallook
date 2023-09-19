package com.seallook.androidx.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentMainBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding, MainGraphViewModel>(
    FragmentMainBinding::inflate,
) {
    override val viewModel: MainGraphViewModel by viewModels(ownerProducer = {
        findNavController().getBackStackEntry(R.id.mainFragment)
    })

    override fun viewModelVariableId(): Int = BR.vm

    private val iconHomeActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_home_filled) }
    private val iconCalendarActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_calendar_filled) }
    private val iconChatActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_chat_filled) }
    private val iconMypageActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_mypage_filled) }
    private val iconDiaryActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_filled) }
    private val iconDiarySettingActive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_setting_filled) }
    private val iconHomeInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_home) }
    private val iconCalendarInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_calendar) }
    private val iconChatInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_chat) }
    private val iconMypageInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_mypage) }
    private val iconDiaryInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary) }
    private val iconDiarySettingInactive by lazy { ContextCompat.getDrawable(requireContext(), R.drawable.ic_diary_setting) }

    override fun onViewCreatedAfterBinding() {
        binding.apply {
            val navController = mainNavigationContainer.getFragment<NavHostFragment>().navController

            homeItem.setOnClickListener {
                onBottomMenuItemClick(R.id.homeContainerFragment, navController)
            }
            calendarItem.setOnClickListener {
                onBottomMenuItemClick(R.id.calendarContainerFragment, navController)
            }
            chatItem.setOnClickListener {
                onBottomMenuItemClick(R.id.chatContainerFragment, navController)
            }
            mypageItem.setOnClickListener {
                onBottomMenuItemClick(R.id.mypageContainerFragment, navController)
            }
            diaryItem.setOnClickListener {
                onBottomMenuItemClick(R.id.diaryContainerFragment, navController)
            }
            diarySettingItem.setOnClickListener {
                onBottomMenuItemClick(R.id.diarySettingContainerFragment, navController)
            }

            backButton.setOnClickListener {
                viewModel.submitToolbarBackEvent()
            }

            viewModel.showToolbarBackButton
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .onEach { backButton.isVisible = it }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            viewModel.toolbarTitle
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .onEach { toolbarTitle.text = getString(it) }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            addDestinationChangeListener(navController)
            addOnBackPressedCallback(navController)
        }
    }

    private fun navigateTo(destinationId: Int, navController: NavController) {
        navController.navigate(
            destinationId,
            null,
            navOptions {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            },
        )
    }

    private fun onBottomMenuItemClick(destinationId: Int, navController: NavController) {
        if (navController.currentDestination?.id == destinationId) {
            viewModel.submitBackToGraphRootEvent()
        } else {
            navigateTo(destinationId, navController)
        }
    }

    private fun addOnBackPressedCallback(navController: NavController) {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    navController.run {
                        val startDestinationId = graph.findStartDestination().id
                        isEnabled = currentBackStackEntry?.destination?.id != startDestinationId
                        if (isEnabled) {
                            popBackStack(startDestinationId, false)
                        }
                    }
                }
            },
        )
    }

    private fun addDestinationChangeListener(navController: NavController) {
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?,
                ) {
                    if (_binding == null) {
                        navController.removeOnDestinationChangedListener(this)
                        return
                    }
                    binding.apply {
                        homeItem.setImageDrawable(iconHomeInactive)
                        calendarItem.setImageDrawable(iconCalendarInactive)
                        chatItem.setImageDrawable(iconChatInactive)
                        mypageItem.setImageDrawable(iconMypageInactive)
                        diaryItem.setImageDrawable(iconDiaryInactive)
                        diarySettingItem.setImageDrawable(iconDiarySettingInactive)

                        destination.hierarchy.forEach {
                            when (it.id) {
                                R.id.homeContainerFragment -> homeItem.setImageDrawable(iconHomeActive)
                                R.id.calendarContainerFragment -> calendarItem.setImageDrawable(iconCalendarActive)
                                R.id.chatContainerFragment -> chatItem.setImageDrawable(iconChatActive)
                                R.id.mypageContainerFragment -> mypageItem.setImageDrawable(iconMypageActive)
                                R.id.diaryContainerFragment -> diaryItem.setImageDrawable(iconDiaryActive)
                                R.id.diarySettingContainerFragment -> diarySettingItem.setImageDrawable(iconDiarySettingActive)
                            }
                        }
                    }
                }
            },
        )
    }
}
