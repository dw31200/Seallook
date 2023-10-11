package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentUpdateCounselorBasicInfoBinding
import com.seallook.androidx.domain.model.CounselorInfoModel
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/* TODO
    1.GetMainImage: 대표사진 등록 클릭시 갤러리 혹은 카메라 앱에서 가져오기
    2.GetOfficeItem: 소속기관 등록 클릭시 네비 갔다와서 해당 기관 아이템 가져오기
    3.GetFile: 증빙서류 등록 클릭시 디바이스에서 가져오기
    4.ListView: 가져온 정보들 리스트로 보여주기
    5.Navigation: 대표사진 등록 > 갤러리/카메라, 소속기관 등록 > UpdateOffice, 증빙서류 등록 > 디바이스 저장소,
        다음 > 상담 유형 선택
 */
@AndroidEntryPoint
class UpdateCounselorBasicInfoFragment : BaseFragment<FragmentUpdateCounselorBasicInfoBinding, UpdateCounselorBasicInfoViewModel>(
    FragmentUpdateCounselorBasicInfoBinding::inflate,
) {
    override val viewModel: UpdateCounselorBasicInfoViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private var photoUri: Uri? = null
    private var fileUri: Uri? = null
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.counselorThumbnailImage.setImageURI(uri)
            photoUri = uri
            Timber.d("Selected URI: $uri")
        } else {
            Timber.d("No media selected")
        }
    }
    private val pickMediaFile = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.fileNameText.text = "첨부 완료"
            fileUri = uri
            Timber.d("Selected URI: $uri")
        } else {
            Timber.d("No media selected")
        }
    }

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            updateThumbnailImageButton.setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            updateCounselingTypeButton.setOnClickListener {
                findNavController().navigate(R.id.action_updateCounselorBasicInfoFragment_to_updateCounselingTypeFragment)
            }
            updateOfficeButton.setOnClickListener {
                findNavController().navigate(R.id.action_updateCounselorBasicInfoFragment_to_updateOfficeFragment)
            }
            updateFileButton.setOnClickListener {
                pickMediaFile.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            finishButton.setOnClickListener {
                val user = viewModel.currentUser.value ?: return@setOnClickListener
                viewModel.uploadFile(
                    "counselor/thumbnail",
                    "$user.png",
                    photoUri ?: return@setOnClickListener,
                )
                viewModel.updateCounselingType()
                viewModel.setCounselorInfo(
                    CounselorInfoModel(
                        "홍길동",
                        "소개",
                        "https://firebasestorage.googleapis.com/v0/b/seallook-9d51d.appspot.com/o/counselor%2Fthumbnail%2Fcom.google.firebase.auth.internal.zzx%4056fc1eb.png?alt=media&token=c95f9239-a7c2-4dab-b49b-8f2593a4ecbf&_gl=1*1rpckjf*_ga*NDkyNDg4NzAyLjE2OTA5NTQxMDM.*_ga_CW55HF8NVT*MTY5Njk0MDA5MC4xMDMuMS4xNjk2OTQwMTI2LjI0LjAuMA..",
                    ),
                )
            }
        }
    }
}
