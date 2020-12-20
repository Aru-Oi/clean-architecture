package com.aru_oi.cleanarchitecture.domain.entitiy

class ComicList(val items: List<Comic>) {
    fun getComicIds() = items.map { it.id }

    fun updateThumbNail(thumbnailList: ThumbnailList) {
        items.forEach { comic ->
            thumbnailList.items.forEach {
                if (it.id == comic.id) {
                    comic.thumbnail = it.thumbnail
                }
            }
        }
    }
}
