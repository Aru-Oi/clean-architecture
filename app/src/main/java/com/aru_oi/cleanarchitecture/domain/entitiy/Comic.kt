package com.aru_oi.cleanarchitecture.domain.entitiy

class Comic(
    val id: Int,
    val title: String,
    val date: String,
    val category: Category,
    var thumbnail: String = ""
) {
    enum class Category {
        ALL, YOUNG, ADULT
    }
}
