package com.demo.demoapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.demoapp.apicall.ApiInterface
import com.demo.demoapp.apicall.ServiceCallBack
import com.demo.demoapp.model.LoginModel
import org.json.JSONObject

class LoginViewModel:ViewModel(), ServiceCallBack {

    var loginLiveData= MutableLiveData<LoginModel>()

fun loginApiCall(jsonObject: JSONObject)
{
    ApiInterface.callAPI(
        ApiInterface.getApi().login(
            jsonObject.toString()), 1, this
    )
}
    override fun handleResponse(t: Any?, resultCode: Int) {
if(t!=null) {
    var loginDetails = t as LoginModel
    loginLiveData.postValue(loginDetails)
}
        else
    loginLiveData.postValue(null)
    }

    override fun handleError(error: Throwable?, resultCode: Int) {

    }
}