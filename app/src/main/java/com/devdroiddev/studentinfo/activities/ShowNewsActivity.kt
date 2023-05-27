package com.devdroiddev.studentinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.databinding.ActivityNewsBinding
import com.devdroiddev.studentinfo.databinding.ActivityShowNewsBinding
import com.devdroiddev.studentinfo.models.ArticleModel

class ShowNewsActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the intent here that is passing from Adapter and Set the value on the View
        val article = intent.getParcelableExtra<ArticleModel>("articles")

        // Load the image using Glide
        Glide.with(this).load(article?.urlToImage).into(binding.showNewsImage)
        // Concatenate "By " with the author name
        binding.showNewsAuthor.text = article?.author
        binding.showNewsTitle.text = article?.title
        binding.showNewsDescription.text = article?.description

    }
}