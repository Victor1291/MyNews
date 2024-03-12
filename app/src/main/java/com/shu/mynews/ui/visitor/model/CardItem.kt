package com.shu.mynews.ui.visitor.model

data class CardItem(
    override val id: String = "card",
    val image: String,
    val title: String,
    val description: String,
) : HasStringId
