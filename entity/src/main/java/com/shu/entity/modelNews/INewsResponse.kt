package com.shu.entity.modelNews


interface INewsResponse {
    val articles: List<Article>
    val status: String
    val totalResults: Int
}