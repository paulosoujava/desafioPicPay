package com.picpay.core.repository

import com.picpay.core.data.User

interface UserDataSource {
    suspend fun add(user: User)
    suspend fun  getAll(): List<User>

}