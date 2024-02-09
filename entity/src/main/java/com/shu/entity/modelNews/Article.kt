package com.shu.entity.modelNews

interface Article {
    val id: Int
    val author: String?
    val content: String?
    val description: String?
    val publishedAt: String?
    val title: String?
    val url: String?
    val urlToImage: String?
}