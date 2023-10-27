package com.seallook.androidx.ui.auth.signup.select

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.share.UserType

@BindingAdapter("bind:setOnClickUserType", "bind:userType", requireAll = true)
fun MaterialButton.setOnClickUserType(
    navigation: SelectSignUpTypeNavigation,
    userType: UserType,
) {
    setOnClickListener {
        when (userType) {
            UserType.CLIENT -> {
                navigation.navigateToSignUp(UserType.CLIENT)
            }
            UserType.COUNSELOR -> {
                navigation.navigateToSignUp(UserType.COUNSELOR)
            }
            UserType.OFFICE -> {
                navigation.navigateToSignUp(UserType.OFFICE)
            }
        }
    }
}
