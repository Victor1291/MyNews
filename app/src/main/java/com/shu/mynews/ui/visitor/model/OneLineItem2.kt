package com.shu.mynews.ui.visitor.model

import com.shu.mynews.ui.visitor.model.HasStringId

data class OneLineItem2(
    override val id: String = "one line",
    val left: String,
    val right: String
) : HasStringId