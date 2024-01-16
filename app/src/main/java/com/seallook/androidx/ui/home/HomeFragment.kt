package com.seallook.androidx.ui.home

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import com.seallook.androidx.ui.home.adapter.OfficeListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

/* TODO
    1.서버에서 가져온 CounselorList DB에 저장 및 flow로 가져오기
    2.서버에서 가져온 계정 타입 DB에 저장 및 flow로 가져오기
    필요한 모델: CounselorInfo(id 자동생성으로 변경, documentId 추가), OfficeInfo(id 자동생성으로 변경, documentId 추가)
 */
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, Effect>(
        FragmentHomeBinding::inflate,
    ),
    HomeNavigation,
    HomeShowWebSite {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val googleLocation = GoogleLocation(WeakReference(this)) {
        viewModel.getLocation(it)
    }

    override fun onViewCreatedAfterBinding() {
        googleLocation.getLocation()
        with(binding) {
            officeList.adapter = OfficeListAdapter()
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            showWebSite = this@HomeFragment
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToReserveCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReserveCounselingFragment(email)
        navigate(action)
    }

    override fun navigateToReservedClient(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReservedClientListFragment(email)
        navigate(action)
    }

    override fun navigateToReservedCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReservedCounselingListFragment(email)
        navigate(action)
    }

    override fun navigateToSearchCounselor() {
        val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
        navigate(action)
    }

    override fun show(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }
}
