package com.aru_oi.cleanarchitecture.datasource

// android FW依存のimport書いてもOK

import com.aru_oi.cleanarchitecture.domain.entitiy.Comic
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicList
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import com.aru_oi.cleanarchitecture.exception.ComicFailure
import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.functional.Result
import com.aru_oi.cleanarchitecture.functional.Result.Failure
import com.aru_oi.cleanarchitecture.functional.Result.Success

// 依存先がUseCaseパッケージを向いていることがポイント。Interface使って依存性の逆転をしている
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IComicSearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicSearchDB : IComicSearchRepository {
    override suspend fun find(comicSearch: ComicSearch): Result<ComicList, FeatureFailure> =
        withContext(Dispatchers.IO) {
            // UseCaseからもらうのはEntityとなり、UseCaseに返すのもEntityとなる
            val keyword = comicSearch.keyword
            val category = comicSearch.category
            val sort = comicSearch.sort

            // 指定されてキーワードやカテゴリーソートに従ってDBから値を取得
            // sortが抽象的なパラメーターで渡されていることがポイント
            // UseCaseはDBから取得することを知らないのでDBのカラム名とかSQL文とか
            // そういう指定の仕方をしてこない

            //以下DBから取得できたと仮定してEntityをUseCaseに返却する
            val comicList = ComicList(
                mutableListOf(
                    Comic(1, "ワンパンマン", "2021年12月", Comic.Category.YOUNG),
                    Comic(2, "終末のハーレム", "2021年11月", Comic.Category.ADULT),
                    Comic(3, "デスノート", "2021年10月", Comic.Category.YOUNG),
                    Comic(4, "俺だけレベルアップな件", "2021年9月", Comic.Category.YOUNG),
                    Comic(5, "ケンガンアシュラ", "2021年8月", Comic.Category.YOUNG),
                    Comic(6, "チェーンソーマン", "2021年7月", Comic.Category.YOUNG),
                    Comic(7, "エアマスター", "2021年6月", Comic.Category.YOUNG),
                    Comic(8, "リボーンの棋士", "2021年5月", Comic.Category.YOUNG),
                    Comic(9, "進撃の巨人", "2021年4月", Comic.Category.YOUNG),
                    Comic(10, "異世界料理道", "2021年3月", Comic.Category.YOUNG),
                    Comic(11, "蜘蛛ですが何か", "2021年2月", Comic.Category.YOUNG),
                    Comic(12, "いちご100%", "2021年1月", Comic.Category.ADULT)
                )
            )
            if (comicList.items.isEmpty()) {
                return@withContext Failure(ComicFailure.NonExistentComic())
            }
            return@withContext Success(comicList)
        }
}
