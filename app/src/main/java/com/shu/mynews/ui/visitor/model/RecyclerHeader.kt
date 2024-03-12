package com.shu.mynews.ui.visitor.model

import com.shu.mynews.ui.visitor.model.HasStringId

data class RecyclerHeader(
    override val id: String = "header",
    val text: String
) : HasStringId
