package com.androbim.umariwenservices.updateuser

import com.androbim.umariwenservices.createuser.UserParameters

data class UserResponse2(val userParameters2:UserParameters2)
data class UserParameters2(val id:String,val  name:String,val email:String,val status:String)
