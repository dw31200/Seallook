package com.seallook.androidx.ui.reserve.counseling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.seallook.androidx.base.Effect
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingSelectDate
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReserveCounselingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<Effect>(), ReserveCounselingSelectDate {
    var email = savedStateHandle.get<String>("email")

    private val _selectedDate = MutableLiveData<LocalDate>()
    val selectedDate: LiveData<LocalDate>
        get() = _selectedDate

    override fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }
}
