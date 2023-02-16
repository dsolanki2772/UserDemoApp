package com.example.userapp.data.dao

import androidx.room.TypeConverter
import com.example.userapp.data.model.*
import com.google.gson.Gson

class DataConverter : java.io.Serializable {
    @TypeConverter
    fun fromName(name: Name): String {
        return Gson().toJson(name)
    }

    @TypeConverter
    fun toName(str: String): Name {
        return Gson().fromJson(str, Name::class.java)
    }

    @TypeConverter
    fun fromLoation(location: Location): String {
        return Gson().toJson(location)
    }

    @TypeConverter
    fun toLoation(str: String): Location {
        return Gson().fromJson(str, Location::class.java)
    }

    @TypeConverter
    fun fromLogin(login: Login): String {
        return Gson().toJson(login)
    }

    @TypeConverter
    fun toLogin(str: String): Login {
        return Gson().fromJson(str, Login::class.java)
    }

    @TypeConverter
    fun fromDob(dob: Dob): String {
        return Gson().toJson(dob)
    }

    @TypeConverter
    fun toDob(str: String): Dob {
        return Gson().fromJson(str, Dob::class.java)
    }

    @TypeConverter
    fun fromId(id: Id): String {
        return Gson().toJson(id)
    }

    @TypeConverter
    fun toId(str: String): Id {
        return Gson().fromJson(str, Id::class.java)
    }

    @TypeConverter
    fun fromPicture(pic: Picture): String {
        return Gson().toJson(pic)
    }

    @TypeConverter
    fun toPicture(str: String): Picture {
        return Gson().fromJson(str, Picture::class.java)
    }
}