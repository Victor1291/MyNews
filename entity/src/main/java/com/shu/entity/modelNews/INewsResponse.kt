package com.shu.entity.modelNews


interface INewsResponse {
    val articles: List<IArticle>
    val status: String
    val totalResults: Int
}