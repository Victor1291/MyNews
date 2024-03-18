package com.shu.mynews.ui.collections

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shu.entity.icollection.Collections
import com.shu.mynews.databinding.DialogItemBinding

class ListCollectionAdapter(
    private val onClick: (Collections) -> Unit
) : ListAdapter<Collections, CollectionViewHolder>(DiffUtilCallback()) {
    private val dejavuChecked = false
    private val dejavuUnChecked = false

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            checkBox.text = item.name
           // count.text = item.total.toString()
            checkBox.isChecked = item.checked

            /*checkBox.setOnCheckedChangeListener { compoundButton, b ->
                item.checked = b
            }*/

            checkBox.setOnClickListener {
                item?.let {
                   // count.text = item.total.toString()
                    onClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DialogItemBinding.inflate(inflater, parent, false)
        return CollectionViewHolder(binding)
    }
}

class DiffUtilCallback : DiffUtil.ItemCallback<Collections>() {
    override fun areItemsTheSame(oldItem: Collections, newItem: Collections): Boolean =
        oldItem.collectionId == newItem.collectionId

    override fun areContentsTheSame(oldItem: Collections, newItem: Collections): Boolean =
        oldItem.name == newItem.name

}

class CollectionViewHolder(val binding: DialogItemBinding) : RecyclerView.ViewHolder(binding.root) {

}
