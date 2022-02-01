package com.example.testalef.Retrofit

import com.example.testalef.Models.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers


interface API {
    @get:GET("list.php")
    val photos: Observable<List<String>>
}