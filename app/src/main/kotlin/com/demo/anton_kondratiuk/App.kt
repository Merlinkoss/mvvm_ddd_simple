package com.demo.anton_kondratiuk

import android.app.Application
import com.demo.anton_kondratiuk.di.AppModule
import com.demo.anton_kondratiuk.di.DI
import com.demo.anton_kondratiuk.di.DataModule
import toothpick.ktp.KTP

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KTP.openRootScope()
                .openSubScope(DI.MAIN_SCOPE)
                .installModules(AppModule(this), DataModule())
                .inject(this)
    }
}