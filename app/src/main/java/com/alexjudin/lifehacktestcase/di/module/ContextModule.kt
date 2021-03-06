package com.alexjudin.lifehacktestcase.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(val context: Context) {
    @Provides
    fun provideContext(): Context = context
}