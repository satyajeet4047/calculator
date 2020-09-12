package com.example.calculator.ui.authentication

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.data.NetworkServiceRepository
import com.example.githubapp.util.RequestStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val networkServiceRepository: NetworkServiceRepository) : ViewModel() {


    private  val TAG = "LoginViewModel"
     var email : String? = null
     var password : String? = null

    private val disposables = CompositeDisposable()

    private val requestStatus : MutableLiveData<RequestStatus>  = MutableLiveData()

    fun getRequestStatus() : MutableLiveData<RequestStatus> {
        return requestStatus
    }

    fun doLogin() {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            requestStatus.value = RequestStatus.INVALID_INPUT
            return
        }

        requestStatus.value = RequestStatus.IN_PROGRESS

        val disposable = networkServiceRepository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "doLogin: Success")
                requestStatus.value = RequestStatus.SUCCESS
            }, {
                //sending a failure callback
                Log.d(TAG, "doLogin: Failure" )
                requestStatus.value = RequestStatus.FAILURE
            })
        disposables.add(disposable)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}