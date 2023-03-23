package com.androbim.umariwenservices.retrofitInstance

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CallRetrofit {

    //https://gorest.co.in/public/v2/users

    val BASE_URL="https://gorest.co.in/public/v2/";

    fun retrofitInstance(): Retrofit
    {
        val retrofit=
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}