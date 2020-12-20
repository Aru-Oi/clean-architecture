package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.domain.entitiy.Comic
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import com.aru_oi.cleanarchitecture.domain.usecase.core.IInputData

// UseCaseが扱えるようにするための入力用のDataクラス。
// UseCaseは入力先を知らないのでこのDataクラスはどんな入力を要求されたとしても
// 対応できるような抽象的な構成にしなければならない。
data class ComicInputData(
    val keyword: String,
    val category: Comic.Category,
    val sort: ComicSearch.Sort
) : IInputData
