package com.shu.mynews.ui.visitor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.CardItemBinding
import com.shu.mynews.databinding.OneLineItem2Binding
import com.shu.mynews.databinding.RecyclerHeaderItemBinding
import com.shu.mynews.databinding.TwoLineItemBinding
import com.shu.mynews.ui.visitor.model.HasStringId

class BaseListAdapter(
    private val clickListener: AdapterClickListenerById,
    private val viewHoldersManager: ViewHoldersManager
) : ListAdapter<HasStringId, BaseListAdapter.DataViewHolder>(BaseDiffCallback()) {

    inner class DataViewHolder(
        private val binding: ViewBinding,
        private val holder: ViewHolderVisitor
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HasStringId, clickListener: AdapterClickListenerById) =
            holder.bind(binding, item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = viewHoldersManager.getViewHolder(viewType)

        val bindingNew = when (holder.layout) {
            R.layout.card_item -> {
                CardItemBinding.inflate(inflater, parent, false)
            }

            R.layout.recycler_header_item -> {
                RecyclerHeaderItemBinding.inflate(inflater, parent, false)
            }

            R.layout.one_line_item_2 -> {
                OneLineItem2Binding.inflate(inflater, parent, false)

            }

            R.layout.two_line_item -> {
                TwoLineItemBinding.inflate(inflater, parent, false)
            }

            else -> {
                throw IllegalArgumentException("Wrong type")
            }
        }
        return DataViewHolder(bindingNew, holder)

    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    override fun getItemViewType(position: Int): Int =
        viewHoldersManager.getItemType(getItem(position))
}

class BaseDiffCallback : DiffUtil.ItemCallback<HasStringId>() {
    override fun areItemsTheSame(oldItem: HasStringId, newItem: HasStringId): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HasStringId, newItem: HasStringId): Boolean =
        oldItem == newItem
}