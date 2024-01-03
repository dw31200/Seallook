package com.seallook.androidx.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.lang.ref.WeakReference

class GoogleLocation(
    private val fragment: WeakReference<Fragment>,
    private val onPermissionsGranted: (Location?) -> Unit,
) {
    private val fusedLocationClient: FusedLocationProviderClient? by lazy {
        fragment.get()?.let {
            LocationServices.getFusedLocationProviderClient(it.requireActivity())
        }
    }

    private val requestPermissionLauncher =
        fragment.get()?.registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) {
            getLocation()
        }

    fun getLocation() {
        if (checkedPermissions()) {
            fusedLocationClient?.lastLocation
                ?.addOnSuccessListener { location: Location? ->
                    onPermissionsGranted(location)
                }
        } else {
            requestPermissionLauncher?.launch(
                REQUIRED_PERMISSIONS,
            )
        }
    }

    private fun checkedPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all { permission ->
            fragment.get()?.let {
                ContextCompat.checkSelfPermission(it.requireContext(), permission) == PackageManager.PERMISSION_GRANTED
            } ?: false
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
