package com.devdroiddev.studentinfo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devdroiddev.studentinfo.R
import com.devdroiddev.studentinfo.activities.FormActivity
import com.devdroiddev.studentinfo.activities.ShowNewsActivity
import com.devdroiddev.studentinfo.models.ArticleModel

class NewsAdapter(val context: Context, val article: List<ArticleModel>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val articles = article[position]
        holder.newstitle.text = articles.title
        holder.newsdescription.text = articles.description
        Glide.with(context).load(articles.urlToImage).into(holder.newsImage)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ShowNewsActivity::class.java)
            intent.putExtra("articles", articles)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
       return article.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var newsImage  = itemView.findViewById<ImageView>(R.id.news_image)
        var newstitle  = itemView.findViewById<TextView>(R.id.news_title)
        var newsdescription  = itemView.findViewById<TextView>(R.id.news_description)
    }
}