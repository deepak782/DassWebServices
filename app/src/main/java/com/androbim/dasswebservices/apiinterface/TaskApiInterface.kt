package com.androbim.dasswebservices.apiinterface

import com.androbim.dasswebservices.task1.Task11Model
import com.androbim.dasswebservices.task1.Task1Model
import com.androbim.dasswebservices.task2.Task2Model
import com.androbim.dasswebservices.task2.Task2ModelItem
import com.androbim.dasswebservices.task3.Task3Model
import retrofit2.Call
import retrofit2.http.GET

interface TaskApiInterface {

    ////https://run.mocky.io/v3/7260d8b8-828d-4558-bfb1-6a0516c61ddd

    @GET("7260d8b8-828d-4558-bfb1-6a0516c61ddd")
    fun getTask1():Call<Task1Model>


    //https://run.mocky.io/v3/dacbc2d5-2487-46bc-ba12-c99a410e3002

    @GET("dacbc2d5-2487-46bc-ba12-c99a410e3002")
    fun getTask11():Call<Task11Model>

    //https://run.mocky.io/v3/417e28c8-988d-462f-830e-efd063a60bf4
    @GET("417e28c8-988d-462f-830e-efd063a60bf4")
    fun getTask2():Call<List<Task2ModelItem>>

    //https://run.mocky.io/v3/482a6837-1770-4f8c-ab33-b2a36b7088fc
    @GET("482a6837-1770-4f8c-ab33-b2a36b7088fc")
    fun  getTask3():Call<Task3Model>


}