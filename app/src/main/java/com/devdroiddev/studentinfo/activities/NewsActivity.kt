package com.devdroiddev.studentinfo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.devdroiddev.studentinfo.adapters.NewsAdapter
import com.devdroiddev.studentinfo.databinding.ActivityNewsBinding
import com.devdroiddev.studentinfo.dbclasses.NewsService
import com.devdroiddev.studentinfo.models.NewsModel
import retrofit2.Call
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    private final val APP_TAG = "My_Retrofit"
    lateinit var newsAdapter : NewsAdapter
    lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()
    }

    private fun getNews() {
        val news = NewsService.newsIntance.getHeadLines("in", 1)
        news.enqueue(object : retrofit2.Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()
                if (news != null) {
                    Log.d(APP_TAG, news.toString())
                    newsAdapter = NewsAdapter(this@NewsActivity, news.articles)
                    binding.newsList.adapter = newsAdapter
                    binding.newsList.layoutManager = LinearLayoutManager(this@NewsActivity)

                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d(APP_TAG, "Error in Fetching New", t)

            }
        })
    }
}