package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.functional.Result
import com.aru_oi.cleanarchitecture.ui.comic.ComicViewModel

interface IComicPresenter {
    suspend fun handle(result: Result<ComicOutputData, FeatureFailure>)
    suspend fun onResult(onResult: (result: Result<List<ComicViewModel>, FeatureFailure>) -> Unit)
}
