package com.seallook.androidx.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seallook.androidx.databinding.DialogProgressBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = ProgressWidget::class,
            attribute = "bind:message",
            method = "setMessage",
        ),
        BindingMethod(
            type = ProgressWidget::class,
            attribute = "bind:visible",
            method = "setVisible",
        ),
        BindingMethod(
            type = ProgressWidget::class,
            attribute = "bind:showFailMessage",
            method = "showFailMessage",
        ),
    ],
)
class ProgressWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = DialogProgressBinding.inflate(
        LayoutInflater.from(context),
        this,
        true,
    )
    private var progressDialog: AlertDialog? = null

    fun setMessage(message: String?) {
        binding.messageTextView.text = message
    }

    fun setVisible(visible: Boolean?, enforce: Boolean = false) {
        if (enforce) {
            dismissProgressDialog()
        } else {
            if (isProgressDialogShown()) return
        }
        if (visible == true) {
            progressDialog = MaterialAlertDialogBuilder(context)
                .setView(binding.root)
                .setCancelable(false)
                .create()
            progressDialog?.show()
        } else if (visible == false) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    fun showFailMessage(visible: Boolean?) {
        if (visible == true) {
            Toast.makeText(
                context,
                "로그인에 실패하였습니다.",
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    private fun isProgressDialogShown() = progressDialog != null
}
