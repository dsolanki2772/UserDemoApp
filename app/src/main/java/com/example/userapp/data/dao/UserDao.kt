package com.example.userapp.data.dao

import androidx.room.*
import com.example.userapp.data.model.UserDetails

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<UserDetails>)

    @Update
    fun update(user: UserDetails)

    @Delete
    fun delete(user: UserDetails)

    @Query("select * from user_table")
    fun getAllUsers(): List<UserDetails>
}