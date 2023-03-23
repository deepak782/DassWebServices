package com.androbim.umariwenservices.createuser

data class UserResponse(val userParameters:UserParameters?)
data class UserParameters(val name:String,val email:String,val gender:String,val status:String)
