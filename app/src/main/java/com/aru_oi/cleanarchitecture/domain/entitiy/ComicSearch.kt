package com.aru_oi.cleanarchitecture.domain.entitiy

class ComicSearch(val keyword: String, val category: Comic.Category, val sort: Sort) {
    init {
        // キーワードは10文字以上の入力は受け付けない
        if (keyword.length > 10) throw RuntimeException()
    }

    // コミック発売日の新しい順で表示するのか、古い順で表示するのかの定義
    // 抽象的に定義すること。SQL文とかで書くのはダメ
    enum class Sort {
        NEW, OLD
    }
}
