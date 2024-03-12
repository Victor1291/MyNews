package com.shu.mynews.ui.visitor.model

interface HasStringId {
    val id: String
    override fun equals(other: Any?): Boolean
}
