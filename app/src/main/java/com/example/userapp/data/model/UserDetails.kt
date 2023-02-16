package com.example.userapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDetails (
    val gender: String = "",
    val name: Name,
    val location: Location,
    @PrimaryKey
    val email: String = "",
    val login: Login,
    val dob: Dob,
    val registered: Dob,
    val phone: String = "",
    val cell: String = "",
    val id: Id,
    val picture: Picture,
    val nat:  String = "",
)
