package com.example.mytestapp.view

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApp : DaggerApplication()
{
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().create(this)
}