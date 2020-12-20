package com.aru_oi.cleanarchitecture.ui.comic

// このクラスはViewの表示のためのデータークラスなのでUseCaseやEntity層には置かない
data class ComicItem(
    val title: String,
    val date: String,
    val rating: String,
    val thumbnailUri: String
)