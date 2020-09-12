package com.example.calculator.ui.calculation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.data.NetworkServiceRepository
import com.example.githubapp.util.RequestStatus
import javax.inject.Inject

class CalculatordashboardViewModel @Inject constructor(private val networkServiceRepository: NetworkServiceRepository) : ViewModel() {

    private val requestStatus : MutableLiveData<RequestStatus> = MutableLiveData()

    private val loginStatus : MutableLiveData<Boolean> = MutableLiveData()

    fun getloginStatus() : MutableLiveData<Boolean> {

        return loginStatus
    }

    fun getRequestStatus() : MutableLiveData<RequestStatus> {
        return requestStatus
    }

    fun checkLoginStatus(){
        loginStatus.value = networkServiceRepository.currentUser() != null
    }


}