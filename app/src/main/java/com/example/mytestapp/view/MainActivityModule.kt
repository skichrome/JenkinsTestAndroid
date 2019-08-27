package com.example.mytestapp.view

import com.example.mytestapp.model.GitRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainActivityModule
{
    @Module
    companion object
    {
        @JvmStatic
        @Provides
        internal fun providesMainViewModelFactory(gitRepository: GitRepository): MainViewModelFactory
        {
            return MainViewModelFactory(gitRepository)
        }
    }

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity
}