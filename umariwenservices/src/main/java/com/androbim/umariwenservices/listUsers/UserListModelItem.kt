package com.androbim.umariwenservices.listUsers

data class UserListModelItem(
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val status: String
)