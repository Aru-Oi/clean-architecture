package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.domain.entitiy.ComicList
import com.aru_oi.cleanarchitecture.domain.entitiy.ThumbnailList
import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.functional.Result

interface IThumbnailRepository {
    suspend fun find(id: ComicList): Result<ThumbnailList, FeatureFailure>
}