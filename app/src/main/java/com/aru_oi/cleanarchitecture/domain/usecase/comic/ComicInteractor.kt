package com.aru_oi.cleanarchitecture.domain.usecase.comic

import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import com.aru_oi.cleanarchitecture.exception.ComicFailure
import com.aru_oi.cleanarchitecture.exception.NetFailure
import com.aru_oi.cleanarchitecture.functional.Result.Failure
import com.aru_oi.cleanarchitecture.functional.Result.Success

class ComicInteractor(
    private val comicSearchRepositoryIF: IComicSearchRepository,
    private val thumbnailRepositoryIF: IThumbnailRepository,
) : IComicUseCase {
    private lateinit var presenterIF: IComicPresenter

    override fun setPresenter(presenter: IComicPresenter) {
        this.presenterIF = presenter
    }

    override suspend fun execute(inputData: ComicInputData) {
        // Entity生成。InputDataを変換してEntityにしているがInputDataの中にEntityが入っていてもよい。
        val comicSearch = ComicSearch(inputData.keyword, inputData.category, inputData.sort)
        // interfaceなのでデーター取得先が何に変更されてもUseCaseに修正は発生しない
        val comicSearchResult = comicSearchRepositoryIF.find(comicSearch)
        if (comicSearchResult is Success) {
            val comicList = comicSearchResult.data
            val thumbnailResult = thumbnailRepositoryIF.find(comicList)
            return if (thumbnailResult is Success) {
                // UseCaseは何をするのかは知っているけど、どうやってそれを実現するのかは知らない
                // idが一致したらComicインスタンスのthumbnailを更新するという具体的な処理はEntityが行う
                comicList.updateThumbNail(thumbnailResult.data)
                presenterIF.handle(Success(ComicOutputData(comicList)))
            } else {
                presenterIF.handle(Failure(NetFailure.NoConnection()))
            }
        }
        presenterIF.handle(Failure(ComicFailure.NonExistentComic()))
    }
}
