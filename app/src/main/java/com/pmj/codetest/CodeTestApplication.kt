package com.pmj.codetest

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.pmj.codetest.di.homeModule
import com.pmj.codetest.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodeTestApplication : Application() {

    companion object {
        lateinit var instance: CodeTestApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        //disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidContext(this@CodeTestApplication)
            modules(listOf(networkModule, homeModule))
        }
    }

}