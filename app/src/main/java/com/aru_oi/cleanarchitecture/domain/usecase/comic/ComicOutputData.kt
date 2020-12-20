package com.aru_oi.cleanarchitecture.domain.usecase.comic

// FW非依存なのでimport文にandroid FWが入っていはいけない

import com.aru_oi.cleanarchitecture.domain.entitiy.ComicList
import com.aru_oi.cleanarchitecture.domain.usecase.core.IOutputData

// UseCaseが扱う出力用のDataクラス。
// UseCaseは出力先を知らないのでこのDataクラスはどんな出力を要求されたとしても
// 対応できるような抽象的な構成にしなければならない。
data class ComicOutputData(val comicList: ComicList) : IOutputData