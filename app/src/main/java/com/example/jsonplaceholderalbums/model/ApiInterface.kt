package com.example.jsonplaceholderalbums.model

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    //https://jsonplaceholder.typicode.com/albums
    @GET("albums")
    fun getPosts() :
            Observable<ArrayList<Album>>
}