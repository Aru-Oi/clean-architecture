package com.aru_oi.cleanarchitecture.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aru_oi.cleanarchitecture.extention.loadFromUrl
import com.aru_oi.cleanarchitecture.ui.comic.ComicAdapter
import com.aru_oi.cleanarchitecture.ui.comic.ComicViewModel


@BindingAdapter("comicItems")
fun bindComicItems(recyclerView: RecyclerView, items: List<ComicViewModel>) {
    val adapter = recyclerView.adapter as ComicAdapter
    adapter.update(items)
}

@BindingAdapter("comicThumbnail")
fun bindThumbnail(imageView: ImageView, thumbnailUri: String) {
    imageView.loadFromUrl(thumbnailUri)
}
