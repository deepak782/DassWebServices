package com.androbim.umariwenservices.apiIntertace

import com.androbim.umariwenservices.createuser.UserParameters
import com.androbim.umariwenservices.createuser.UserResponse
import com.androbim.umariwenservices.listUsers.UserListModelItem
import com.androbim.umariwenservices.searchwithid.IDModel
import com.androbim.umariwenservices.searchwithname.NameModelItem
import com.androbim.umariwenservices.updateuser.UserParameters2
import com.androbim.umariwenservices.updateuser.UserResponse2
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyApinterface {

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun getUserList() : Call<List<UserListModelItem>>

    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun createUser(@Body userParameters: UserParameters):Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun updateUser(@Path ("user_id") user_id:String,@Body userParameters2: UserParameters2):Call<UserResponse2>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun deleteUser(@Path ("user_id") user_id:String):Call<UserResponse2>

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun searchWithName(@Query ("name") name:String):Call<List<NameModelItem>>

    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun searchWithID(@Path ("user_id") id:String):Call<IDModel>

}