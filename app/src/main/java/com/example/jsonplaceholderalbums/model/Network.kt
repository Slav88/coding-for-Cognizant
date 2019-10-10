package com.example.jsonplaceholderalbums.model

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class Network {

    companion object {
        @SuppressLint("CheckResult")
        fun initRetrofit():
                MutableLiveData<ArrayList<Album>> {
            val mutableLiveData =
                MutableLiveData<ArrayList<Album>>()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            //RXKotlin
            retrofit.create(ApiInterface::class.java)
                .getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { mutableLiveData.value = it },
                    onError = { print(it.message) }
                )
            return mutableLiveData
        }
    }
}