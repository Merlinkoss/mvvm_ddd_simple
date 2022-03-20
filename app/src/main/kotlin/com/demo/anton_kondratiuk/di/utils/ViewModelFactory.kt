package com.demo.anton_kondratiuk.di.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.anton_kondratiuk.di.DI.MAIN_SCOPE
import toothpick.Toothpick
import javax.inject.Inject
import javax.inject.Singleton

// Helper for binding ViewModels with injection
@Singleton
class ViewModelFactory @Inject constructor() :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        Toothpick.openScope(MAIN_SCOPE).getInstance(modelClass) as T
}