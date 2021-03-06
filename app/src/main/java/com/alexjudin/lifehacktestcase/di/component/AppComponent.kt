package com.alexjudin.lifehacktestcase.di.component

import com.alexjudin.lifehacktestcase.di.module.ContextModule
import com.alexjudin.lifehacktestcase.di.module.DataModule
import com.alexjudin.lifehacktestcase.di.module.DomainModule
import com.alexjudin.lifehacktestcase.di.module.PresentationModule
import com.alexjudin.lifehacktestcase.ui.activity.MainActivity

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

}