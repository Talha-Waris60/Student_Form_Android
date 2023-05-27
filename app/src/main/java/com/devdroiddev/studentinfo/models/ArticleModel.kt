package com.devdroiddev.studentinfo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleModel(
    val author : String,
    val title : String,
    val description : String,
    val url : String,
    val urlToImage : String
) : Parcelable