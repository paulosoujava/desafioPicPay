package com.picpay.core.usecase

import com.picpay.core.data.User
import com.picpay.core.repository.UserRepository

class GetAllUser(val userRepository: UserRepository) {
    //we want an operator so that we can simply invoke this class as an operator on an object
    suspend operator fun invoke() = userRepository.getAll()
}