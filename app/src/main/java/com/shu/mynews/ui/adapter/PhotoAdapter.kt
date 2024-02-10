package com.shu.mynews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shu.entity.IPhoto
import com.shu.mynews.R
import com.shu.mynews.databinding.ItemPhotoBinding

class PhotoAdapter  : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val callback = object : DiffUtil.ItemCallback<IPhoto>() {
        override fun areItemsTheSame(oldItem: IPhoto, newItem: IPhoto): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(oldItem: IPhoto, newItem: IPhoto): Boolean {
            return oldItem.pathPhoto == newItem.pathPhoto
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = differ.currentList[position]


            Glide
                .with(holder.binding.articleImage)
                .load(photo.pathPhoto)
                .placeholder(R.drawable.bg_cover_placeholder)
                .into(holder.binding.articleImage)
            with(holder.binding) {
                articleImage.clipToOutline = true
                articleDate.text = photo.data
                articleImage.setOnClickListener {
                    onItemClickListener?.let { it(photo) }
                }
            }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((IPhoto) -> Unit)? = null

    fun setOnItemClickListener(listener: (IPhoto) -> Unit) {
        onItemClickListener = listener
    }

}