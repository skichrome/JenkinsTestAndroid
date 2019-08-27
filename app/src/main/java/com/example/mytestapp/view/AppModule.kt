package com.example.mytestapp.view

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule
{
    @Provides
    fun providesContext(application: MainApp): Context = application.applicationContext
}