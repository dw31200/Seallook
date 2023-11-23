package com.seallook.androidx.ui.auth.signup

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("bind:setTextInProfileInfo")
fun TextInputEditText.setTextInProfileInfo(profileInfo: String?) {
    if (profileInfo == null) setText("") else setText(profileInfo)
}

@BindingAdapter("bind:setErrorMessage")
fun TextInputLayout.setErrorMessage(error: String?) {
    if (error != null) setError(error)
}

@BindingAdapter("bind:checkedButtonId")
fun setCheckedButtonId(group: MaterialButtonToggleGroup, id: Int?) {
    if (group.checkedButtonId != id && id != null) {
        group.check(id)
    }
}

@InverseBindingAdapter(attribute = "bind:checkedButtonId", event = "checkedChanged")
fun getGender(group: MaterialButtonToggleGroup): Int {
    return group.checkedButtonId
}

@BindingAdapter("checkedChanged")
fun setListeners(
    group: MaterialButtonToggleGroup,
    attrChange: InverseBindingListener,
) {
    group.addOnButtonCheckedListener { _, checkedId, isChecked ->
        if (isChecked) {
            attrChange.onChange()
        }
        if (group.checkedButtonId != checkedId) {
            attrChange.onChange()
        }
    }
}
