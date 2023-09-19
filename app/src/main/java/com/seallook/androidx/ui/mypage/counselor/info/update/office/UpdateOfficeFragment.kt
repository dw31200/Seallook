package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentUpdateOfficeBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetOfficeList: 검색창 돋보기 클릭시 검색창 텍스트가 이름에 들어간 기관 가져오기
    2.ListView: 가져온 리스트 보여주기
    3.Navigation: 기관 아이템/뒤로가기 > BasicInfo
 */
@AndroidEntryPoint
class UpdateOfficeFragment : BaseFragment<FragmentUpdateOfficeBinding, UpdateOfficeViewModel>(
    FragmentUpdateOfficeBinding::inflate,
) {
    override val viewModel: UpdateOfficeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
