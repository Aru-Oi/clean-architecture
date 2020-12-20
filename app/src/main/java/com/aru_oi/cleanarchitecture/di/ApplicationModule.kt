package com.aru_oi.cleanarchitecture.di

import com.aru_oi.cleanarchitecture.datasource.ComicSearchDB
import com.aru_oi.cleanarchitecture.datasource.ThumbnailNet
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IComicSearchRepository
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IThumbnailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideComicSearch(
    ): IComicSearchRepository = ComicSearchDB()

    @Provides
    fun provideThumbnail(
    ): IThumbnailRepository = ThumbnailNet()
}
