package com.shu.mynews.ui.visitor.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.RecyclerHeaderItemBinding
import com.shu.mynews.ui.visitor.model.RecyclerHeader
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.ViewHolderVisitor

class HeaderViewHolder : ViewHolderVisitor {

    override val layout = R.layout.recycler_header_item

    override fun bind(binding: ViewBinding, item: Any, clickListener: AdapterClickListenerById) {
        (binding as RecyclerHeaderItemBinding).header.text = (item as RecyclerHeader).text
    }

    override fun acceptBinding(item: Any): Boolean = item is RecyclerHeader
}