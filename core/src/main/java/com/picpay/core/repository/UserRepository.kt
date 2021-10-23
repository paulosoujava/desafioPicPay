package com.picpay.core.repository

import com.picpay.core.data.User

class UserRepository( val datasource: UserDataSource) {
    suspend fun addUser(user: User) = datasource.add(user)
    suspend fun getAll() = datasource.getAll()
}