package com.example.mytestapp.model

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class GitLocalReposDataSource
{
    fun getRepositories(): Observable<ArrayList<Repository>>
    {
        val arrayList = ArrayList<Repository>()

        arrayList.add(Repository("First", "Owner 1", 132, false))
        arrayList.add(Repository("Second", "Owner 2", 423, true))
        arrayList.add(Repository("Third", "Owner 3", 36, false))

        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }

    fun saveRepositoriesInLocalDB(arrayList: ArrayList<Repository>): Completable
    {
//        return Completable.complete().delay(1, TimeUnit.SECONDS) // Don't work because when completable is completed, no more operators works (delay() here)
        return Single.just(1).delay(1,TimeUnit.SECONDS).ignoreElement()  // toCompletable() deprecated, replaced by ignoreElement()
    }

//    fun getRepositories(onLocalRepoReadyListener: OnLocalRepoReadyListener)
//    {
//        val arrayList = ArrayList<Repository>()
//
//        arrayList.add(Repository("First", "Owner 1", 132, false))
//        arrayList.add(Repository("Second", "Owner 2", 423, true))
//        arrayList.add(Repository("Third", "Owner 3", 36, false))
//
//        Handler().postDelayed({ onLocalRepoReadyListener.onLocalDataReady(arrayList) }, 2000)
//    }

//    interface OnLocalRepoReadyListener
//    {
//        fun onLocalDataReady(data: ArrayList<Repository>)
//    }
}