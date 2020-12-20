package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.domain.entitiy.ComicList
import com.aru_oi.cleanarchitecture.functional.Result
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import com.aru_oi.cleanarchitecture.exception.FeatureFailure

interface IComicSearchRepository {
    suspend fun find(comic: ComicSearch): Result<ComicList, FeatureFailure>
}
