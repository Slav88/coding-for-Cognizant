package com.example.jsonplaceholderalbums.model

import androidx.room.*

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    fun getAll(): List<Album>

    @Insert
    fun insert(title: Album)

    @Delete
    fun delete(todo: Album)

}