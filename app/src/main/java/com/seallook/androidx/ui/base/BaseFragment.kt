package com.seallook.androidx.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination.Companion.createRoute
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seallook.androidx.databinding.DialogProgressBinding
import kotlinx.coroutines.launch

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel<E>, E : Effect>(
    private val inflate: (
        LayoutInflater,
        ViewGroup?,
        Boolean,
    ) -> T,
) : Fragment() {
    protected var _binding: T? = null
        private set
    protected val binding
        get() = _binding!!
    protected abstract val viewModel: VM

    protected abstract fun viewModelVariableId(): Int

    private var progressDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(
            viewModelVariableId(),
            viewModel,
        )
        binding.lifecycleOwner = this

        onViewCreatedAfterBinding()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect {
                    if (it != null) {
                        onEffectCollect(it)
                        viewModel.clearEffect()
                    }
                }
            }
        }
    }

    abstract fun onViewCreatedAfterBinding()

    abstract fun onEffectCollect(effect: E)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigate(directions: NavDirections) {
        findNavController().navigate(directions.actionId, directions.arguments, null)
    }

    protected fun navigate(directions: NavDirections, navOptions: NavOptions?) {
        findNavController().navigate(directions.actionId, directions.arguments, navOptions)
    }

    protected fun navigate(directions: NavDirections, navigatorExtras: Navigator.Extras) {
        findNavController().navigate(directions.actionId, directions.arguments, null, navigatorExtras)
    }

    protected fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit) {
        findNavController().navigate(route, navOptions(builder))
    }

    protected fun navigate(
        route: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null,
    ) {
        findNavController().navigate(
            NavDeepLinkRequest.Builder.fromUri(createRoute(route).toUri()).build(),
            navOptions,
            navigatorExtras,
        )
    }

    protected fun showProgressDialog(message: String, enforce: Boolean = false) {
        if (enforce) {
            dismissProgressDialog()
        } else {
            if (isProgressDialogShown()) return
        }
        val binding = LayoutInflater.from(requireContext()).let {
            DialogProgressBinding.inflate(it)
        }

        binding.messageTextView.text = message

        progressDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setCancelable(false)
            .create()
        progressDialog!!.show()
    }

    protected fun dismissProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    private fun isProgressDialogShown() = progressDialog != null

    protected fun showFailMessage(message: String?) {
        if (message != null) {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }
}
