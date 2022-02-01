package com.example.testalef.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var Instance: Retrofit ?= null

    val instance: Retrofit
        get(){
            if(Instance == null){
                Instance = Retrofit.Builder()
                    .baseUrl("http://dev-tasks.alef.im/task-m-001/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return Instance!!
        }
}