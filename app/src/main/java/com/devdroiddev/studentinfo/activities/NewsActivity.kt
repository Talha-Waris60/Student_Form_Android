package com.devdroiddev.studentinfo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.ThemedSpinnerAdapter.Helper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devdroiddev.studentinfo.adapters.NewsAdapter
import com.devdroiddev.studentinfo.api.RetrofitInstance
import com.devdroiddev.studentinfo.databinding.ActivityNewsBinding

import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.models.ArticleModel
import com.devdroiddev.studentinfo.models.NewsModel
import retrofit2.Call
import retrofit2.Response

class NewsActivity : AppCompatActivity(), OnItemClickListener<ArticleModel>  {

    private  val APP_TAG = "My_Retrofit"
    lateinit var newsAdapter : NewsAdapter
    lateinit var binding : ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()
    }

    private fun getNews() {
        // Make the API request
        val news = RetrofitInstance.newsInterfaceApiInstance.getHeadLines("us", 1)
        news.enqueue(object : retrofit2.Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {

                binding.progressBar.visibility = View.GONE
                val news = response.body()
                if (news != null) {
                    Log.d(APP_TAG, news.toString())
                    newsAdapter = NewsAdapter(this@NewsActivity, news.articles, this@NewsActivity)
                    binding.newsList.adapter = newsAdapter
                    binding.newsList.layoutManager = LinearLayoutManager(this@NewsActivity)

                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@NewsActivity,t.message,Toast.LENGTH_SHORT).show()

            }
        })
    }
    override fun onItemClicked(model: ArticleModel) {
        val intent = Intent(this@NewsActivity, ShowNewsActivity::class.java)
        intent.putExtra("articles", model)
        this.startActivity(intent)
    }
}