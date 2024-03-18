package com.shu.mynews.ui.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shu.entity.icollection.IMessage
import com.shu.mynews.databinding.DialogChildItemBinding

class ChildAdapter(
      private val onClick: (IMessage) -> Unit
) : ListAdapter<IMessage, ChildViewHolder>(DiffUtilCallbackChild()) {

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            checkBox.text = item.message

            checkBox.setOnClickListener {
                item?.let {
                    onClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DialogChildItemBinding.inflate(inflater, parent, false)
        return ChildViewHolder(binding)
    }
}

class DiffUtilCallbackChild : DiffUtil.ItemCallback<IMessage>() {
    override fun areItemsTheSame(oldItem: IMessage, newItem: IMessage): Boolean =
        oldItem.message == newItem.message

    override fun areContentsTheSame(oldItem: IMessage, newItem: IMessage): Boolean =
        oldItem.message == newItem.message

}

class ChildViewHolder(val binding: DialogChildItemBinding) : RecyclerView.ViewHolder(binding.root) {

}