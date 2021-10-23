package com.picpay.presentation.main

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.core.data.User
import com.picpay.core.repository.UserRepository
import com.picpay.core.usecase.AddUser
import com.picpay.core.usecase.GetAllUser
import com.picpay.desafio.android.R
import com.picpay.framework.db.RoomDataSource
import com.picpay.framework.UseCases
import com.picpay.framework.db.UserEntity
import com.picpay.framework.api.Api
import com.picpay.framework.di.ApplicationModule
import com.picpay.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainViewModel(application: Application) : ViewModel() {
     private val coroutineScope = CoroutineScope(Dispatchers.IO)
     //val repository = UserRepository(RoomDataSource(application))

    @Inject
     lateinit var  useCases: UseCases
    //private val useCases = UseCases(AddUser(repository), GetAllUser(repository))

    val progressBar = MutableLiveData<Int>()
    val messageFromApi = MutableLiveData("")
    var listUser: List<User>? = null
    var error = application.resources.getString(R.string.error)

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(application))
            .build()
            .inject(this)
    }


    fun getAllContactsFromApi() {
        progressBar.postValue(View.VISIBLE)
        Api().service.getUsers()
            .enqueue(object : Callback<List<UserEntity>> {
                override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                    messageFromApi.value = error
                    progressBar.postValue(View.GONE)
                }

                override fun onResponse(
                    call: Call<List<UserEntity>>,
                    response: Response<List<UserEntity>>
                ) {
                    listUser = response.body()?.map { it.toUSer() }
                    //kiss Keep It Simple and Stupid!
                    // don't call saveUserFromApiAndMakeCache if isNullOrEmpty
                    if (!listUser.isNullOrEmpty()) {
                        saveUserFromApiAndMakeCache()
                        progressBar.postValue(View.GONE)
                    }

                }
            })
    }

    fun saveUserFromApiAndMakeCache() {
        coroutineScope.launch {
            listUser?.forEach {
                useCases.addUser(it)
            }

        }
    }

    fun getCached() {
        progressBar.postValue(View.VISIBLE)
        var job = coroutineScope.launch {
            listUser = useCases.getAllUser()
        }

        job.invokeOnCompletion {
            progressBar.postValue(View.GONE)
        }
    }


}