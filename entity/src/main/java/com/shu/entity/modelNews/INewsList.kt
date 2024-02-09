package com.shu.entity.modelNews


interface INewsList {

    val category: String?
    val data: List<INewsData>
    val success: Boolean?

}
