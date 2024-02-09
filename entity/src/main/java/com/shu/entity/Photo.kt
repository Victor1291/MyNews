package com.shu.entity

data class Photo(
    override val data: String,
    override val pathPhoto: String,
    override val favorite: Boolean
): IPhoto
