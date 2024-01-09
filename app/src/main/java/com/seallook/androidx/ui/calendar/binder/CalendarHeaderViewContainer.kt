package com.seallook.androidx.ui.calendar.binder

import android.view.View
import com.kizitonwose.calendar.view.ViewContainer
import com.seallook.androidx.databinding.CalendarHeaderLayoutBinding

class CalendarHeaderViewContainer(view: View) : ViewContainer(view) {
    val legendLayout = CalendarHeaderLayoutBinding.bind(view).legendLayout.root
}
