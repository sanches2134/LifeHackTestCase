package com.alexjudin.lifehacktestcase

import android.app.Application
import com.alexjudin.lifehacktestcase.di.component.AppComponent
import com.alexjudin.lifehacktestcase.di.component.DaggerAppComponent

import com.alexjudin.lifehacktestcase.di.module.ContextModule

class LifeHackApp : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        component =
            DaggerAppComponent.builder().contextModule(ContextModule(this.applicationContext))
                .build()
    }
}
