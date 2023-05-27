package com.devdroiddev.studentinfo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(
    val totalResults : Int,
    val articles : List<ArticleModel>
) : Parcelable