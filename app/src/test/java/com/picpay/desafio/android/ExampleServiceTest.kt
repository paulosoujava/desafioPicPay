package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.core.data.User
import com.picpay.framework.api.PicPayService
import com.picpay.framework.db.UserEntity
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()
    private val service = ApiMockServiceService(api)

    @Test
    fun `should return a list of user entity`() {
        // given
        val call = mock<Call<List<UserEntity>>>()
        val expectedUsers = emptyList<UserEntity>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.apiMock()

        // then
        assertEquals(users, expectedUsers)
    }


}