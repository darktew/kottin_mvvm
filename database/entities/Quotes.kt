package com.example.kotlin_mvvm.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quotes(
    @PrimaryKey(autoGenerate = false )
    val id : Int,
    val quote : String,
    val author : String,
    val thumbnail : String,
    val created_at : String?,
    val updated_at : String?
)