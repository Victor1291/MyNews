package com.shu.mynews.ui.visitor.adapter

import androidx.viewbinding.ViewBinding

interface ViewHolderVisitor {
    val layout: Int
    fun acceptBinding(item: Any): Boolean
    fun bind(binding: ViewBinding, item: Any, clickListener: AdapterClickListenerById)
}