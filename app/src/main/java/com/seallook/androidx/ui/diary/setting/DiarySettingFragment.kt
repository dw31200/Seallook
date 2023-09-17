package com.seallook.androidx.ui.diary.setting

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentDiarySettingBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetBottomMenuList: 상담일지 바텀 메뉴 가져오기
    2.Navigation: 완료 혹은 뒤로가기 클릭시 다이어리 리스트 화면 이동
    3.ListView: 가져온 바텀 메뉴 왼쪽 리스트에 보여주기
    4.SetBottomMenuList: 완료 클릭시 체크되어 있는 바텀메뉴로 바텀 메뉴 설정하기. (+ 버튼 클릭 후 EditText에 적힌 메뉴도 포함)
 */
@AndroidEntryPoint
class DiarySettingFragment : BaseFragment<FragmentDiarySettingBinding, DiarySettingViewModel>(
    FragmentDiarySettingBinding::inflate,
) {
    override val viewModel: DiarySettingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
