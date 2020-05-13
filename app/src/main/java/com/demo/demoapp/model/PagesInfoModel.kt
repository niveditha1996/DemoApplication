package com.demo.demoapp.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PagesInfoModel {

    @SerializedName("page")
    @Expose
     val page: Int? = null
    @SerializedName("per_page")
    @Expose
     val perPage: Int? = null
    @SerializedName("total")
    @Expose
     val total: Int? = null
    @SerializedName("total_pages")
    @Expose
     val totalPages: Int? = null
    @SerializedName("data")
    @Expose
     val data: ArrayList<UserInfoModel>? = null
    @SerializedName("ad")
    @Expose
     val ad: AdModel? = null
}