package com.seallook.androidx.ui.widget

import android.content.Context
import android.content.ContextWrapper
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.seallook.androidx.databinding.BirthDatePickerBinding
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class BirthDatePickerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = BirthDatePickerBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )

    var birth: Date? = null
        set(value) {
            field = value
            if (value != null) {
                binding.birthTextField.editText!!.setText(
                    DateUtils.formatDateTime(
                        context,
                        value.time,
                        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NUMERIC_DATE,
                    ),
                )
            }
        }

    init {
        with(binding) {
            birthTextField.editText!!.setOnClickListener {
                showDatePicker()
            }

            birthTextField.setEndIconOnClickListener {
                showDatePicker()
            }
        }
    }

    private fun clearFocusFromEditTexts() {
        binding.birthText.clearFocus()
    }

    private fun showDatePicker() {
        val selection = if (birth == null) Date().toUtc() else birth!!.toUtc()

        val fragmentManager = findFragmentActivity()?.supportFragmentManager
            ?: findFragment()?.childFragmentManager

        if (fragmentManager != null) {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setSelection(selection)
                .build()
                .apply {
                    addOnPositiveButtonClickListener {
                        birth = it.toLocal()
                        clearFocusFromEditTexts()
                    }
                }
            picker.show(fragmentManager, "date_picker")
        }
    }

    // 호스팅 FragmentActivity 찾는 도우미 함수
    private fun findFragmentActivity(): FragmentActivity? {
        var context = this.context
        while (context is ContextWrapper) {
            if (context is FragmentActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    // 호스팅 Fragment 찾는 도우미 함수
    private fun findFragment(): Fragment? {
        var parent = this.parent
        while (parent != null) {
            if (parent is Fragment) {
                return parent
            }
            parent = parent.parent
        }
        return null
    }

    private fun Date.toUtc(): Long {
        val local = Calendar.getInstance().apply { time = this@toUtc }
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        utc.set(
            local.get(Calendar.YEAR),
            local.get(Calendar.MONTH),
            local.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0,
        )
        return utc.timeInMillis
    }

    private fun Long.toLocal(): Date {
        val local = Calendar.getInstance()
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            timeInMillis = this@toLocal
        }

        local.set(
            utc.get(Calendar.YEAR),
            utc.get(Calendar.MONTH),
            utc.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0,
        )
        return local.time
    }
}
