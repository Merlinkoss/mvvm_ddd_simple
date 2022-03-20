package com.demo.anton_kondratiuk.features.extended

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.anton_kondratiuk.models.CountryUIModel
import com.demo.anton_kondratiuk.models.Event
import com.demo.anton_kondratiuk.usecases.GetAllCountryUseCase
import com.demo.anton_kondratiuk.usecases.GetCountriesByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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