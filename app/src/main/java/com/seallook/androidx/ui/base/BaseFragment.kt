package com.seallook.androidx.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seallook.androidx.databinding.DialogProgressBinding

abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel>(
    private val inflate: (
        LayoutInflater,
        ViewGroup?,
        Boolean,
    ) -> T,
) : Fragment() {
    private var _binding: T? = null
    protected val binding
        get() = _binding!!
    protected abstract val viewModel: VM

    protected abstract fun viewModelVariableId(): Int

    protected var progressDialog: AlertDialog? = null
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
    }

    abstract fun onViewCreatedAfterBinding()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    protected fun isProgressDialogShown() = progressDialog != null
}
