package com.picpay.framework.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DatabaseServiceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var dao: UserDao
    private lateinit var db: DatabaseService


    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java
        ).allowMainThreadQueries().build()
        dao = db.userDao()
    }

    @After
    fun teardown() {
        db.close()
    }


    @Test
    fun insertAUser() = runBlockingTest {
        val paulo = UserEntity(1, "Paulo", "paulojorge", "")
        dao.addUserEntity(paulo)

        val users = dao.getAllUserEntity()

        assert(users.contains(paulo))
    }
}