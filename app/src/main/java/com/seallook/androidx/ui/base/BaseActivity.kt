package com.seallook.androidx.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

abstract class BaseActivity<T : ViewDataBinding, VM : BaseViewModel<E>, E : Effect>(
    private val inflate: (LayoutInflater) -> T,
) : AppCompatActivity() {
    protected lateinit var binding: T
        private set

    protected abstract val viewModel: VM

    protected abstract fun viewModelVariabledId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        binding.setVariable(
            viewModelVariabledId(),
            viewModel,
        )

        binding.lifecycleOwner = this

        onCreateAfterBinding()

        lifecycleScope.launch {
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

    abstract fun onCreateAfterBinding()

    abstract fun onEffectCollect(effect: E)
}
