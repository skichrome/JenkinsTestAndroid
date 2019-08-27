package com.example.mytestapp.model

import android.os.Handler
import com.example.mytestapp.androidmanagers.NetManager
import io.reactivex.Observable
import javax.inject.Inject

class GitRepository@Inject constructor(private val netManager: NetManager)
{
    // =======================================
    //                  Fields
    // =======================================

    private val localDataSource = GitLocalReposDataSource()
    private val remoteDataSource = GitRemoteRepoDataSource()

    // Get simple data

    fun refreshData(onDataReadyListener: OnDataReadyListener) =
        Handler().postDelayed({ onDataReadyListener.onDataReady("some new data available") }, 2000)

    interface OnDataReadyListener
    {
        fun onDataReady(data: String)
    }

    // Get Repositories

    fun getRepositories(): Observable<ArrayList<Repository>>
    {
        netManager.isConnectedToInternet?.let { it ->
            if (it)
                return remoteDataSource.getRemoteRepositories()
                    .flatMap {
                        return@flatMap localDataSource.saveRepositoriesInLocalDB(it)
                            .toSingleDefault(it)
                            .toObservable()
                    }
        }
        return localDataSource.getRepositories()
    }
}

//interface OnRepositoryReadyListener
//{
//    fun onRepositoryReady(data: ArrayList<Repository>)
//}