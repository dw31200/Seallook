package com.seallook.androidx.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import com.seallook.androidx.ui.home.adapter.OfficeListAdapter
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.KakaoSearchUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import timber.log.Timber

@BindingAdapter("bind:setList")
fun RecyclerView.setList(list: List<CounselorInfoUiModel>?) {
    (adapter as? HomeAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:setOfficeList")
fun RecyclerView.setOfficeList(list: List<KakaoSearchUiModel>?) {
    (adapter as? OfficeListAdapter)?.fetchData(list ?: emptyList())
}

@BindingAdapter("bind:setDistance")
fun TextView.setDistance(distance: String?) {
    text = if (distance != null) "${distance}m" else ""
}

@BindingAdapter("bind:onOfficeItemClickListener")
fun RecyclerView.setOnOfficeItemClickListener(homeShowWebSite: HomeShowWebSite) {
    (adapter as? OfficeListAdapter)?.homeShowWebSite = homeShowWebSite
}

@BindingAdapter("bind:onItemClickListener")
fun RecyclerView.setCounselorItemClickListener(homeNavigation: HomeNavigation) {
    (adapter as? HomeAdapter)?.homeNavigation = homeNavigation
}

@BindingAdapter("bind:officeUrl")
fun ImageView.setOfficeUrl(url: String) {
    this.findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
        var imageUrl: String? = null

        withContext(Dispatchers.IO) {
            val doc: Document = Jsoup.connect(url).get()

            val ogImageElement: Element? = doc.select("meta[property=og:image]").first()

            if (ogImageElement != null) {
                imageUrl = ogImageElement.attr("content")
                Timber.d("$imageUrl")
            }
        }

        withContext(Dispatchers.Main) {
            imageUrl?.let {
                Glide
                    .with(this@setOfficeUrl)
                    .load(it)
                    .into(this@setOfficeUrl)
            }
        }
    }
}
