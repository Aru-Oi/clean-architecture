package com.aru_oi.cleanarchitecture.presenter

import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.functional.Result
import com.aru_oi.cleanarchitecture.functional.Result.Failure
import com.aru_oi.cleanarchitecture.functional.Result.Success
import com.aru_oi.cleanarchitecture.ui.comic.ComicItem
import com.aru_oi.cleanarchitecture.ui.comic.ComicViewModel
import com.aru_oi.cleanarchitecture.domain.usecase.comic.ComicOutputData
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IComicPresenter

class ComicPresenter : IComicPresenter {
    private var callback: (result: Result<List<ComicViewModel>, FeatureFailure>) -> Unit = { _ -> }

    override suspend fun handle(result: Result<ComicOutputData, FeatureFailure>) {
        result.fold(::handleComicOutputData, ::handleFailure)
    }

    override suspend fun onResult(onResult: (result: Result<List<ComicViewModel>, FeatureFailure>) -> Unit) {
        callback = onResult
    }

    private fun handleComicOutputData(outputData: ComicOutputData) {
        // PresenterはUseCaseから受け取った抽象的なデータを出力先が扱いやすいように変換する役割
        val result =
            outputData.comicList.items.map {
                ComicViewModel(
                    ComicItem(
                        it.title,
                        it.date,
                        it.category.name,
                        it.thumbnail
                    )
                )
            }
        callback(Success(result))
    }

    private fun handleFailure(failure: FeatureFailure) {
        callback(Failure(failure))
    }
}
