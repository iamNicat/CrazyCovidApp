package com.nicathaciyev.crazycovid.data.responses

data class NewsItem (
    val body : String,
    val datetime : String,
    val id : String,
    val title : String,
    var expandable : Boolean = false
)