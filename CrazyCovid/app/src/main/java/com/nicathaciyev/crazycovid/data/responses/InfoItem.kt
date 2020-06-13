package com.nicathaciyev.crazycovid.data.responses

import com.google.gson.annotations.SerializedName

data class InfoItem
    (
    val title : String,
    val body : String,
    var expandable : Boolean = false
)