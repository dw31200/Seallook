package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentUpdateCounselorBasicInfoBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UpdateCounselorBasicInfoFragment :
    BaseFragment<FragmentUpdateCounselorBasicInfoBinding, UpdateCounselorBasicInfoViewModel, UpdateCounselorBasicInfoEffect>(
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
            updateOfficeButton.setOnClickListener {
                val action = UpdateCounselorBasicInfoFragmentDirections.actionUpdateCounselorBasicInfoFragmentToUpdateOfficeFragment()
                navigate(action)
            }
            updateFileButton.setOnClickListener {
                pickMediaFile.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            finishButton.setOnClickListener {
                if (photoUri != null) {
                    viewModel.uploadFile(
                        "counselor/thumbnail",
                        photoUri ?: return@setOnClickListener,
                        binding.nameTextField.editText?.text.toString(),
                        binding.prTextField.editText?.text.toString(),
                    )
                }
            }
        }
    }

    override fun onEffectCollect(effect: UpdateCounselorBasicInfoEffect) {
        when (effect) {
            UpdateCounselorBasicInfoEffect.UploadFile -> {
                showProgressDialog("상담사 정보를 업로드 중입니다.")
            }
            UpdateCounselorBasicInfoEffect.SuccessSetOfficeCounselorEmail -> {
                dismissProgressDialog()
                val action = UpdateCounselorBasicInfoFragmentDirections.actionUpdateCounselorBasicInfoFragmentToMypageFragment()
                navigate(action)
            }

            UpdateCounselorBasicInfoEffect.FailureSetOfficeCounselorEmail -> {
                showFailMessage("업로드에 실패했습니다.")
                dismissProgressDialog()
            }
            UpdateCounselorBasicInfoEffect.FailureUpdateCounselorInfo -> {
                showFailMessage("업로드에 실패했습니다.")
                dismissProgressDialog()
            }
            UpdateCounselorBasicInfoEffect.FailureUpdateCounselorOfficeId -> {
                showFailMessage("업로드에 실패했습니다.")
                dismissProgressDialog()
            }
            UpdateCounselorBasicInfoEffect.FailureUpdateOfficeInfo -> {
                showFailMessage("업로드에 실패했습니다.")
                dismissProgressDialog()
            }
            else -> Unit
        }
    }
}
