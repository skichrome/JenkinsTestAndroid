package com.example.mytestapp.model

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class GitRemoteRepoDataSource
{
    fun getRemoteRepositories(): Observable<ArrayList<Repository>>
    {
        val arrayList = ArrayList<Repository>()

        arrayList.add(Repository("First", "Owner 1", 132, false))
        arrayList.add(Repository("Second", "Owner 2", 423, true))
        arrayList.add(Repository("Third", "Owner 3", 36, false))

        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }

    fun saveRepositoriesInRemoteDB(arrayList: ArrayList<Repository>)
    {
        //Todo save in database
    }
}