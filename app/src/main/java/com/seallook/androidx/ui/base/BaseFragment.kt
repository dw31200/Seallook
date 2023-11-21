package com.seallook.androidx.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.DialogProgressBinding
import kotlinx.coroutines.launch

/* TODO
    1. BaseFragment에서 Effect 쓸 수 있게 수정하기
    2. ProgressBar 관련 삭제 및 관련 프레그먼트 수정
 */

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
