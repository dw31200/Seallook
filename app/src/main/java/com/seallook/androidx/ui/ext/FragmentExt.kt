package com.seallook.androidx.ui.ext

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.seallook.androidx.R

fun Fragment.findRootNavController(): NavController =
    requireActivity().findNavController(R.id.fragment_container)
