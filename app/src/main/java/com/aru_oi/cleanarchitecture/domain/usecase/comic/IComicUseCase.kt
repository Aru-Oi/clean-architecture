package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.domain.usecase.core.IUseCase

// クリーンアーキテクチャのInput Boundaryに該当する
interface IComicUseCase: IUseCase<ComicInputData> {
    fun setPresenter(presenter: IComicPresenter)
}
