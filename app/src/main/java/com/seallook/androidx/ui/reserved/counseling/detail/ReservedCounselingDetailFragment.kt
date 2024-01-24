package com.seallook.androidx.ui.reserved.counseling.detail

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingDetailBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedCounselingDetailFragment :
    BaseFragment<FragmentReservedCounselingDetailBinding, ReservedCounselingDetailViewModel, Effect>(
        FragmentReservedCounselingDetailBinding::inflate,
    ),
    ReservedCounselingDetailShowWebSite {
    override val viewModel: ReservedCounselingDetailViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        binding.showWebSite = this@ReservedCounselingDetailFragment
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun show(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }
}
