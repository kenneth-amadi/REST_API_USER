package com.example.pregnancyyyapp.network

import com.example.pregnancyyyapp.BuildConfig
import com.example.pregnancyyyapp.model.UserInfo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("empl")
    fun fetchUserInfo(): Call<UserInfo>

    companion object {
        private const val BaseURL = "https://myofficerest.herokuapp.com/office/user/getall/"
        fun getClient(): ApiInterface {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.connectTimeout(30, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            builder.readTimeout(30, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logging)
            }
            builder.cache(null)
            val okHttpClient = builder.build()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }

    }
}