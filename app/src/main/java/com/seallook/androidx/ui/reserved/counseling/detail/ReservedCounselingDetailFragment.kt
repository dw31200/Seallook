package com.seallook.androidx.ui.reserved.counseling.detail

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingDetailBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedCounselingDetailFragment :
    BaseFragment<FragmentReservedCounselingDetailBinding, ReservedCounselingDetailViewModel, ReservedCounselingDetailEffect>(
        FragmentReservedCounselingDetailBinding::inflate,
    ),
    ReservedCounselingDetailShowWebSite {
    override val viewModel: ReservedCounselingDetailViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        binding.showWebSite = this@ReservedCounselingDetailFragment
    }

    override fun onEffectCollect(effect: ReservedCounselingDetailEffect) {
        when (effect) {
            ReservedCounselingDetailEffect.UpdateConfirm -> {
                showProgressDialog("업로드 중입니다.")
            }
            ReservedCounselingDetailEffect.SuccessUpdateConfirm -> {
                dismissProgressDialog()
            }
            ReservedCounselingDetailEffect.FailureUpdateConfirm -> {
                dismissProgressDialog()
                showFailMessage("업로드에 실패했습니다.")
            }
        }
    }

    override fun show(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }
}
