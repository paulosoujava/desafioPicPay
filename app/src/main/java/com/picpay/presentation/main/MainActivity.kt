package com.picpay.presentation.main

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.core.data.User
import com.picpay.desafio.android.R
import com.picpay.presentation.main.ui.UserListAdapter
import com.picpay.util.checkForInternet

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpRecycler()

        viewModelFactory = MainActivityViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initObservables()

        if (checkForInternet(this))
            viewModel.getAllContactsFromApi()
        else
            viewModel.getCached()
    }

    //************************************************
    //PRIVATE METHODS
    //************************************************
    private fun initObservables() {
        observerError()
        observerProgress()
    }

    private fun setUpRecycler() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observerError() {
        viewModel.messageFromApi.observe(this, {
            if (it.isNotEmpty())
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT)
                    .show()
        })
    }

    private fun showListRecycler(listUser: List<User>?) {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.visibility = View.VISIBLE
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.users = listUser!!


    }

    private fun observerProgress() {
        viewModel.progressBar.observe(this, {
            progressBar.visibility = it
            if (!viewModel.listUser.isNullOrEmpty()) {
                showListRecycler(viewModel.listUser)
            }
        })


    }

}
