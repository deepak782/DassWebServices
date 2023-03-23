package com.androbim.dasswebservices.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CallRetrofit {


    val BASE_URL="https://run.mocky.io/v3/";

    fun retrofitInstance():Retrofit
    {
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}