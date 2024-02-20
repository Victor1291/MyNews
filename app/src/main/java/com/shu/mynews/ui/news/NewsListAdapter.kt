package com.shu.mynews.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shu.entity.modelNews.IArticle
import com.shu.mynews.R
import com.shu.mynews.databinding.NewsItemBinding

class NewsListAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<IArticle>() {
        override fun areItemsTheSame(oldItem: IArticle, newItem: IArticle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: IArticle, newItem: IArticle): Boolean {
            return oldItem.url == newItem.url
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = differ.currentList[position]
        //notifyItemRemoved(0)
        holder.itemView.apply {
            Glide
                .with(this)
                .load(item.urlToImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(
                    CenterCrop(),
                    RoundedCorners(10)
                )
                .placeholder(R.drawable.bg_cover_placeholder)
                //.skipMemoryCache(true)
                .into(holder.binding.poster)
            with(holder.binding) {
                text.text = item?.content
                title.text = item?.title
                titleDate.text = item?.publishedAt
            }
            setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    private var onItemClickListener: ((IArticle) -> Unit)? = null

    fun setOnItemClickListener(listener: (IArticle) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}

class NewsViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)


