package com.shu.mynews.ui.visitor.viewHolders

import androidx.viewbinding.ViewBinding
import com.shu.mynews.R
import com.shu.mynews.databinding.OneLineItem2Binding
import com.shu.mynews.ui.visitor.adapter.AdapterClickListenerById
import com.shu.mynews.ui.visitor.adapter.ViewHolderVisitor
import ru.alexmaryin.recycleronvisitor.data.ui_models.OneLineItem2



class OneLine2ViewHolder : ViewHolderVisitor {
    override val layout: Int = R.layout.one_line_item_2

    override fun acceptBinding(item: Any): Boolean = item is OneLineItem2

    override fun bind(
        binding: ViewBinding,
        item: Any,
        clickListener: AdapterClickListenerById
    ) {
        with((binding as OneLineItem2Binding)) {
            with(item as OneLineItem2) {
                text1.text = item.left
                text2.text= item.right
            }
        }

    }
}