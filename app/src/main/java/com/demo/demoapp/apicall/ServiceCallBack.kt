package com.demo.demoapp.apicall

interface ServiceCallBack {
    fun handleResponse(`object`: Any?, resultCode: Int)
    fun handleError(error: Throwable?, resultCode: Int)
}