package com.shu.mynews.ui.visitor.model

import com.shu.mynews.ui.visitor.model.HasStringId

data class TwoStringsItem(
    override val id: String = "two strings",
    val caption: String,
    val details: String
) : HasStringId
