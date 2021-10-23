package com.picpay.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.picpay.util.checkForInternet
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainViewModelTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel


    @Test
    fun `when no has connection call cache `(){
        val mockReturnInternetFalse = false
        viewModel = MainViewModel(ApplicationProvider.getApplicationContext())

        viewModel.useCases
    }
}