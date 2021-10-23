package com.picpay.framework

import com.picpay.core.usecase.AddUser
import com.picpay.core.usecase.GetAllUser
//This class basically instantiate all the use cases and use them
//in our architecture, in ur view models especially
class UseCases(
    val addUser: AddUser,
    val getAllUser: GetAllUser
)