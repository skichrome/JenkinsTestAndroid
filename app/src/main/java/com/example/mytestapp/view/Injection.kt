package com.example.mytestapp.view

import android.content.Context
import com.example.mytestapp.androidmanagers.NetManager
import com.example.mytestapp.model.GitRepository

object Injection
{
    // =======================================
    //                  Fields
    // =======================================

    private var NET_MANAGER: NetManager? = null

    fun provideNetManager(context: Context): NetManager
    {
        if (NET_MANAGER == null)
            NET_MANAGER = NetManager(context)
        return NET_MANAGER!!
    }

    fun provideGitRepository(netManager: NetManager): GitRepository = GitRepository(netManager)

    fun provideMainViewModelFactory(context: Context): MainViewModelFactory
    {
        val netManager = provideNetManager(context)
        val repository = provideGitRepository(netManager)
        return MainViewModelFactory(repository)
    }
}