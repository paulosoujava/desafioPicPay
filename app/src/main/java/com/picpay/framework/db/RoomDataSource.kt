package com.picpay.framework.db

import android.content.Context
import com.picpay.core.data.User
import com.picpay.core.repository.UserDataSource

class RoomDataSource(context: Context): UserDataSource {
    private val userDao = DatabaseService.getInstance(context).userDao()
    override suspend fun add(user: User) = userDao.addUserEntity(UserEntity.fromUser(user))
    override suspend fun getAll() = userDao.getAllUserEntity().map { it.toUSer() }
}