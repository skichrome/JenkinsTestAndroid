package com.example.mytestapp.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.mytestapp.BR

class Repository(repositoryName: String, var repositoryOwner: String?, var numberOfStars: Int?, var hasIssues: Boolean = false): BaseObservable()
{
    @get:Bindable
    var mRepositoryName: String = repositoryName
    set(value)
    {
        field = value
        notifyPropertyChanged(BR.mRepositoryName)
    }
}