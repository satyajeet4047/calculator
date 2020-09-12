package com.example.calculator.data

import javax.inject.Inject

class NetworkServiceRepository @Inject constructor(private val networkService: NetworkService) {

    fun login(email: String, password: String) = networkService.login(email, password)


    fun currentUser() = networkService.currentUser()


}