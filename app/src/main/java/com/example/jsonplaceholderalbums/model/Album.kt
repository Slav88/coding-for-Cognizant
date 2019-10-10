package com.example.jsonplaceholderalbums.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "albums")
data class Album(

    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title")val title: String

)