package com.demo.demoapp.apicall

import com.demo.demoapp.model.LoginModel
import com.demo.demoapp.model.PagesInfoModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


public class ApiInterface {

    companion object {

        var retrofit: Retrofit? = null
        var serviceCall: ApiInterface? = null

        fun getInstance(): ApiInterface? {
            if (serviceCall == null) {
                serviceCall =
                    ApiInterface()
            }
            return serviceCall!!
        }

        fun getApi(): ServiceAPI {





            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()


            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit.create(ServiceAPI::class.java)


        }


     fun <T> callAPI(call: Call<T>, resultCode: Int, serviceCallBack: ServiceCallBack) {
         val gson=Gson()
        call.enqueue(object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                serviceCallBack.handleResponse(response.body(), resultCode)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                serviceCallBack.handleError(t, resultCode)
            }
        })
    }
}

    interface ServiceAPI
    {

        @Headers("Content-Type: application/json")
        @POST("/api/login")
        fun login(@Body body:String):Call<LoginModel>


        @GET("/api/users")
        fun getUserList(@Query("page") page:Int):Call<PagesInfoModel>

    }

}