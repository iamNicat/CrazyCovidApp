package com.nicathaciyev.crazycovid.data.responses

import com.google.gson.annotations.SerializedName

data class GlobalItem (
    @SerializedName("cases")
    val globalCases: String,
    @SerializedName("deaths")
    val globalDeath: String,
    @SerializedName("recovered")
    val globalRecovered: String


)