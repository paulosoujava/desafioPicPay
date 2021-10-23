package com.picpay.desafio.android

import com.picpay.framework.api.PicPayService
import com.picpay.framework.db.UserEntity

class ApiMockServiceService(
    private val service: PicPayService
) {

    fun apiMock(): List<UserEntity> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}