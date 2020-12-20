package com.aru_oi.cleanarchitecture.di

import com.aru_oi.cleanarchitecture.presenter.ComicPresenter
import com.aru_oi.cleanarchitecture.domain.usecase.comic.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelModule {
    @Provides
    fun provideComicInteractor(
        comicSearchRepository: IComicSearchRepository,
        thumbnailRepository: IThumbnailRepository
    ): IComicUseCase = ComicInteractor(comicSearchRepository, thumbnailRepository)

    @Provides
    fun provideComicPresenter(
    ): IComicPresenter = ComicPresenter()
}
