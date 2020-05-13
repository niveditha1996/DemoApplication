package com.demo.demoapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.demoapp.apicall.ApiInterface
import com.demo.demoapp.apicall.ServiceCallBack
import com.demo.demoapp.model.PagesInfoModel
import com.demo.demoapp.model.UserInfoModel
import com.demo.demoapp.respository.UserRepo


class UserInfoViewModel(application: Application): AndroidViewModel(application) ,
    ServiceCallBack {
    var userInfoLiveData= MutableLiveData<PagesInfoModel>()
    private var repository: UserRepo = UserRepo(application)
    private var allUsers: LiveData<List<UserInfoModel>> = repository.getAllUsers()

    fun apiCallforUserInfo(page:Int)
    {
        ApiInterface.callAPI(
            ApiInterface.getApi().getUserList(
                page
            ), 2, this
        )
    }



    fun insert(user: UserInfoModel) {
        repository.insert(user)
    }

    fun getAllUsers(): LiveData<List<UserInfoModel>> {
        return allUsers
    }

    override fun handleResponse(t: Any?, resultCode: Int) {


        userInfoLiveData.postValue(t as PagesInfoModel)
        Log.e("onresponse","responded")

    }



    override fun handleError(error: Throwable?, resultCode: Int) {
Log.e("error",error.toString())

    }
}


