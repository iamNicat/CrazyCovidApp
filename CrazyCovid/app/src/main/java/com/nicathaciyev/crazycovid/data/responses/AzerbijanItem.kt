package com.nicathaciyev.crazycovid.data.responses

import com.google.gson.annotations.SerializedName

data class AzerbijanItem(
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


)