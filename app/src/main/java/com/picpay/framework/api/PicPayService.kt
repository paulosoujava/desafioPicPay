package com.picpay.framework.api

import com.picpay.framework.db.UserEntity
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {
    @GET("users")
    fun getUsers(): Call<List<UserEntity>>
}