package com.demo.demoapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class AdModel {

    @SerializedName("company")
    @Expose
     val company: String? = null
    @SerializedName("url")
    @Expose
     val url: String? = null
    @SerializedName("text")
    @Expose
     val text: String? = null

}