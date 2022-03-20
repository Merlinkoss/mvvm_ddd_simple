package com.demo.anton_kondratiuk.features.countryList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.anton_kondratiuk.models.Event
import com.demo.anton_kondratiuk.usecases.GetAllCountryUseCase
import com.demo.anton_kondratiuk.usecases.GetCountriesByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import toothpick.InjectConstructor

@InjectConstructor
class CountryListViewModel(
        private val getAllCountryUseCase: GetAllCountryUseCase,
        private val getCountriesByNameUseCase: GetCountriesByNameUseCase
) : ViewModel() {

    private val _mutableMainState = MutableLiveData<Event<CountryListState>>()
    val state: LiveData<Event<CountryListState>>
        get() = _mutableMainState

    private var updateListJob: Job? = null

    init {
        updateListJob = viewModelScope.launch(Dispatchers.IO) {
            _mutableMainState.postValue(Event.Loading())
            try {
                val mainState = CountryListState(getAllCountryUseCase())
                _mutableMainState.postValue(Event.Complete(mainState))
            } catch (error: Exception) {
                _mutableMainState.postValue(Event.Error(error))
            }
        }
    }

    fun searchByName(value: String?) {
        updateListJob?.cancel()
        updateListJob = viewModelScope.launch(Dispatchers.Default) {
            delay(DEBOUNCE_TIME)
            _mutableMainState.postValue(Event.Loading())
            try {
                val mainState = CountryListState(if (value.isNullOrEmpty()) getAllCountryUseCase() else getCountriesByNameUseCase(value))
                _mutableMainState.postValue(Event.Complete(mainState))
            } catch (error: Exception) {
                _mutableMainState.postValue(Event.Error(error))
            }
        }
    }

    companion object {

        const val DEBOUNCE_TIME = 1500L
    }

}