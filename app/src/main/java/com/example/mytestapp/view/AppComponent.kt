package com.example.mytestapp.view

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent: AndroidInjector<MainApp>
{
    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<MainApp>()
}