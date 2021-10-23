package com.picpay.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserEntity(userEntity: UserEntity)

    // here I can use LIMIT = X(value) and create a pagination
    @Query("SELECT * FROM users")
    suspend fun getAllUserEntity():List<UserEntity>
}