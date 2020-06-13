package com.nicathaciyev.crazycovid.data

import com.nicathaciyev.crazycovid.data.responses.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/countries")
    fun getCountriesData() : Call<List<CountryItem>>

    @GET("/countries/Azerbaijan")
    fun getAzerbaijanData() : Call<AzerbijanItem>

    @GET("/all")
    fun getGlobalData() : Call<GlobalItem>

    @GET("/api/news")
    fun getLocalNews() : Call<List<NewsItem>>

    @GET("/api/articles")
    fun getLocalInfo() : Call<List<InfoItem>>


}