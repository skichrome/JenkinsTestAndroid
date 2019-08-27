package com.example.mytestapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytestapp.R
import com.example.mytestapp.databinding.ActivityMainBinding
import com.example.mytestapp.model.Repository
import com.example.mytestapp.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), RepositoryRVAdapter.OnItemClickListener,
    LifecycleOwner
{
    // =======================================
    //                  Fields
    // =======================================

    private lateinit var mBinding: ActivityMainBinding
    private val mRepositoryRVAdapter = RepositoryRVAdapter(arrayListOf(), this)

    @Inject lateinit var mainViewModelFactory: MainViewModelFactory

    // =======================================
    //           Superclass Methods
    // =======================================

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val viewModelFactory = ViewModelProviders.of(this, Injection.provideMainViewModelFactory(applicationContext)).get(MainViewModel::class.java)
        val viewModelFactory = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel::class.java)
        mBinding.viewmodel = viewModelFactory
        mBinding.executePendingBindings()

        mBinding.repositoryRv.layoutManager = LinearLayoutManager(this)
        mBinding.repositoryRv.adapter = mRepositoryRVAdapter

        viewModelFactory.repositories.observe(this,
            Observer<ArrayList<Repository>> { it -> it?.let { mRepositoryRVAdapter.replaceData(it) } })
    }

    override fun onItemClick(position: Int)
    {
        Toast.makeText(this, "Item clicked : $position", Toast.LENGTH_SHORT).show()
    }
}