package com.demo.demoapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LoginModel {

    @SerializedName("token")
    @Expose
    val token: String? = null

    @SerializedName("error")
    @Expose
    val error: String? = null


}