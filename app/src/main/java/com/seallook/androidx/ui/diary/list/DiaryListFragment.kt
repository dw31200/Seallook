package com.seallook.androidx.ui.diary.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentDiaryListBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetClientList: 내담자 상담일지 가져오기
    2.ListView: 가져온 상담 일지를 선택된 바텀 메뉴에 맞게 정렬하여 리스트로 보여주기
    3.NavigateToSetting: 바텀 퀵메뉴 편집 클릭시 세팅 화면 이동
    4.NavigateToCreate: 새 일지 작성 클릭시 새 일지 작성 화면 이동
    5.NavigateToDetail: 상담일지 아이템 클릭시 상담일지 디테일 화면 이동
 */
@AndroidEntryPoint
class DiaryListFragment : BaseFragment<FragmentDiaryListBinding, DiaryListViewModel>(
    FragmentDiaryListBinding::inflate,
) {
    override val viewModel: DiaryListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
