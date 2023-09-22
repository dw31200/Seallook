package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentUpdateCounselorBasicInfoBinding
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

    private val storage: FirebaseStorage by lazy {
        Firebase.storage
    }
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
                if (photoUri != null) {
                    uploadPhoto(
                        photoUri ?: return@setOnClickListener,
                        successHandler = { uri ->
                            Timber.d("사진업로드 $uri")
                        },
                        errorHandler = {
                            Toast.makeText(requireContext(), "사진 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            }
        }
    }

    private fun uploadPhoto(uri: Uri, successHandler: (String) -> Unit, errorHandler: () -> Unit) {
        val fileName = "${System.currentTimeMillis()}.png"
        storage.reference.child("article/photo").child(fileName)
            .putFile(uri)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    storage.reference.child("article/photo").child(fileName)
                        .downloadUrl
                        .addOnSuccessListener { uri ->
                            successHandler(uri.toString())
                        }.addOnFailureListener {
                            errorHandler()
                        }
                } else {
                    errorHandler()
                }
            }
    }
}
