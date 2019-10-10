package com.example.jsonplaceholderalbums.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jsonplaceholderalbums.model.Album
import com.example.jsonplaceholderalbums.model.Network

class CustomViewModel : ViewModel() {

    private lateinit var data
            : LiveData<ArrayList<Album>>

    init {
        initRetrofit()
    }

    fun getData() : LiveData<ArrayList<Album>> {
        return data
    }

    fun initRetrofit(){
        data = Network.initRetrofit()
    }
}