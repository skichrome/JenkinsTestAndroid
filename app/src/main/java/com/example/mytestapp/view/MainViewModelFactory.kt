package com.example.mytestapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytestapp.model.GitRepository
import com.example.mytestapp.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: GitRepository): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}