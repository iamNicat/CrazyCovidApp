package com.nicathaciyev.crazycovid.data.responses


import com.google.gson.annotations.SerializedName

data class CountryItem(
    val country: String,
    @SerializedName("cases")
    val totalCases: String,
    val todayCases: String,
    @SerializedName("deaths")
    val totalDeaths: String,
    val todayDeaths: String,
    @SerializedName("recovered")
    val totalRecovered: String,
    val critical: String


//    val country: String,
//    @SerializedName("new_cases")
//    val newCases: String,
//    @SerializedName("new_deaths")
//    val newDeaths: String,
//    @SerializedName("total_cases")
//    val totalCases: String,
//    @SerializedName("total_deaths")
//    val totalDeaths: String,
//    @SerializedName("total_recovered")
//    val totalRecovered: String
)