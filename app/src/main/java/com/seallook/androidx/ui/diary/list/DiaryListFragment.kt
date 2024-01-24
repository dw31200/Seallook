package com.seallook.androidx.ui.diary.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentDiaryListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryListFragment : BaseFragment<FragmentDiaryListBinding, DiaryListViewModel, Effect>(
    FragmentDiaryListBinding::inflate,
) {
    override val viewModel: DiaryListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
