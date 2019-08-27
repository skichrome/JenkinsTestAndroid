package com.example.mytestapp.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.extensions.plusAssign
import com.example.mytestapp.model.GitRepository
import com.example.mytestapp.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val gitRepoModel: GitRepository) : ViewModel()
{
    val text = ObservableField("Old data")
    val isLoading = ObservableField(false)

    val repositories = MutableLiveData<ArrayList<Repository>>()

    private val mDisposable = CompositeDisposable() // Composite disposable act as a container of observables, and dispose method is called on each observable (in onCleared())

    private val onDataReadyListener = object : GitRepository.OnDataReadyListener
    {
        override fun onDataReady(data: String)
        {
            isLoading.set(false)
            text.set(data)
        }
    }

    fun refresh()
    {
        isLoading.set(true)
        gitRepoModel.refreshData(onDataReadyListener)
    }

    fun loadRepos()
    {
        isLoading.set(true)
        mDisposable += gitRepoModel.getRepositories()
            .subscribeOn(Schedulers.newThread())    // Run tasks on a new background thread
            .observeOn(AndroidSchedulers.mainThread())  // Run results on the main thread
            .subscribeWith(object : DisposableObserver<ArrayList<Repository>>()
        {
            override fun onNext(data: ArrayList<Repository>)
            {
                repositories.value = data
            }

            override fun onComplete()
            {
                isLoading.set(false)
            }

            override fun onError(e: Throwable) = Unit
        })
    }

    override fun onCleared()
    {
        super.onCleared()
        if (!mDisposable.isDisposed)
            mDisposable.dispose()
    }
}