package com.devdroiddev.studentinfo.dbclasses

import android.telecom.Call
import com.devdroiddev.studentinfo.models.NewsModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


// https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=API_KEY
// https://newsapi.org/v2/everything?q=tesla&from=2023-04-26&sortBy=publishedAt&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"  // Add those part of API that is common in every API
const val API_KEY = "fec7bf0cfd7f486bbee76b27267a91ab"

interface NewsInterface {

    @GET("v2/top-headlines?apiKey=$API_KEY")  // Link the get method with the endPoint where they hit
    fun getHeadLines(@Query("country")country : String, @Query("page")page : Int ) : retrofit2.Call<NewsModel>// Method Ready

    // How they hit the Query
    // https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}

// Object of Retrofit Here
object NewsService{
    val newsIntance : NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsIntance = retrofit.create(NewsInterface::class.java)
    }
}