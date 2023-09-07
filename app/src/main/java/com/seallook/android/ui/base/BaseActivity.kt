package com.seallook.android.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    private val inflate: (LayoutInflater) -> T,
) : AppCompatActivity() {
    private lateinit var binding: T
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        onCreateAfterBinding()
    }

    abstract fun onCreateAfterBinding()
}
