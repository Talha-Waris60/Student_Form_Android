package com.devdroiddev.studentinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityNewsBinding
import com.devdroiddev.studentinfo.databinding.ActivityShowNewsBinding
import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.models.ArticleModel

class ShowNewsActivity : AppCompatActivity(){

    lateinit var binding: ActivityShowNewsBinding
    lateinit var articleModel: ArticleModel
    private val APP_TAG  = "Student_INFO"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_news)
        // get the intent here that is passing from Adapter and Set the value on the View
        Log.d(APP_TAG, "Before Intent")
        articleModel= intent.getParcelableExtra<ArticleModel>("articles")!!
        Log.d(APP_TAG, "After Intent")
        binding.articleModel = articleModel

        // Concatenate "By " with the author name
        /*binding.showNewsAuthor.text = articleModel?.author
        binding.showNewsTitle.text = articleModel?.title
        binding.showNewsDescription.text = articleModel?.description
        // Load the image using Glide
        Glide.with(this).load(articleModel?.urlToImage).into(binding.showNewsImage)*/

    }

}