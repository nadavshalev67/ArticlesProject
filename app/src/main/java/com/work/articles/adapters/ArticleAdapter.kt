package com.work.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.work.articles.R
import com.work.articles.model.ServerArticle
import com.squareup.picasso.Picasso

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {


    inner class ArticleHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val content: TextView = itemView.findViewById(R.id.article_content)
        val title: TextView = itemView.findViewById(R.id.article_content)
        val image: ImageView = itemView.findViewById(R.id.article_image)
    }

    private val differCallback = object : DiffUtil.ItemCallback<ServerArticle>() {
        override fun areItemsTheSame(oldItem: ServerArticle, newItem: ServerArticle) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ServerArticle, newItem: ServerArticle) =
            oldItem.imageUrl == newItem.imageUrl


    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        return ArticleHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_article,
                parent,
                false
            )
        )
    }


    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val article = differ.currentList[position]
        holder.content.text = article.description
        holder.title.text = article.title
        Picasso.get().load(article.imageUrl).into(holder.image)
    }


}
