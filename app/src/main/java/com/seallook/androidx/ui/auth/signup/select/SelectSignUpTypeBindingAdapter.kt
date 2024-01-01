package com.seallook.androidx.ui.auth.signup.select

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.seallook.androidx.share.UserTypeOption

@BindingAdapter("bind:setOnClickUserType", "bind:userType", requireAll = true)
fun MaterialButton.setOnClickUserType(
    navigation: SelectSignUpTypeNavigation,
    userTypeOption: UserTypeOption,
) {
    setOnClickListener {
        when (userTypeOption) {
            UserTypeOption.CLIENT -> {
                navigation.navigateToSignUp(UserTypeOption.CLIENT)
            }
            UserTypeOption.COUNSELOR -> {
                navigation.navigateToSignUp(UserTypeOption.COUNSELOR)
            }
            UserTypeOption.OFFICE -> {
                navigation.navigateToSignUp(UserTypeOption.OFFICE)
            }
        }
    }
}
