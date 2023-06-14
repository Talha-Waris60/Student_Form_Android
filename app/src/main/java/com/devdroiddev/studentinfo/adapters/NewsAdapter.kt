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
import com.devdroiddev.studentinfo.databinding.NewsItemRowBinding
import com.devdroiddev.studentinfo.interfaces.OnItemClickListener
import com.devdroiddev.studentinfo.models.ArticleModel

class NewsAdapter(val context: Context, private val article: List<ArticleModel>,
                private val itemClickListener: OnItemClickListener<ArticleModel>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        /*val inflater= LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)*/

        val inflater = LayoutInflater.from(parent.context)
        val binding: NewsItemRowBinding = NewsItemRowBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val articles = article[position]
        holder.binding.newsItemRowArticleModel = articles
        holder.binding.newItemClickListener = itemClickListener
        holder.binding.executePendingBindings()

        // Calling the interface method here
       /* holder.itemView.setOnClickListener{
            itemClickListener.onItemClicked(articles)
        }*/
      /*  holder.binding.newsAuthor.text = articles.author
          holder.binding.newsTitle.text = articles.title
          holder.binding.newsDescription.text = articles.description
          Glide.with(context).load(articles.urlToImage).into(holder. binding.newsImage)/
         */

      /*  holder.itemView.setOnClickListener{
            val intent = Intent(context, ShowNewsActivity::class.java)
            intent.putExtra("articles", articles)
            context.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
       return article.size
    }

    class MyViewHolder(val binding: NewsItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
      /*  val newsAuthor: TextView = binding.newsAuthor
        val newsTitle: TextView = binding.newsTitle
        val newsDescription: TextView = binding.newsDescription
        val newsImage: ImageView = binding.newsImage*/
    }
}