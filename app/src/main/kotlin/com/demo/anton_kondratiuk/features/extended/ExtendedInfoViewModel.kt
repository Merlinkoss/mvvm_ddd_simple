package com.demo.anton_kondratiuk.features.extended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.anton_kondratiuk.models.CountryUIModel
import com.demo.anton_kondratiuk.models.Event
import toothpick.InjectConstructor

@InjectConstructor
class ExtendedInfoViewModel : ViewModel() {

    private val _mutableMainState = MutableLiveData<Event<ExtendedInfoState>>()
    val state: LiveData<Event<ExtendedInfoState>>
        get() = _mutableMainState

    fun init(countryModel: CountryUIModel?) {
        countryModel?.let {
            _mutableMainState.postValue(Event.Complete(ExtendedInfoState(it)))
        } ?: run {
            _mutableMainState.postValue(Event.Error(error("Can't find country Model")))
        }
    }

}