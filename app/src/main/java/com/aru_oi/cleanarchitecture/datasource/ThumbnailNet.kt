package com.aru_oi.cleanarchitecture.datasource

// android FW依存のimport書いてもOK

import com.aru_oi.cleanarchitecture.domain.entitiy.ComicList
import com.aru_oi.cleanarchitecture.domain.entitiy.Thumbnail
import com.aru_oi.cleanarchitecture.domain.entitiy.ThumbnailList
import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.exception.NetFailure
import com.aru_oi.cleanarchitecture.functional.Result
import com.aru_oi.cleanarchitecture.functional.Result.Success
import com.aru_oi.cleanarchitecture.functional.Result.Failure

// 依存先がUseCase層を向いていることがポイント。Interface使って依存性の逆転をしている
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IThumbnailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ThumbnailNet : IThumbnailRepository {
    override suspend fun find(comicList: ComicList): Result<ThumbnailList, FeatureFailure> =
        withContext(Dispatchers.IO) {
            // UseCaseからもらうのはEntityとなり、UseCaseに返すのもEntityとなる
            // Netに繋がっているかなどAndroid FW依存処理はここにかく
            if (!isNetAvailable()) {
                return@withContext Failure(NetFailure.NoConnection())
            }

            val comicIds = comicList.getComicIds()
            // UseCaseから受け取ったidに紐づく画像を通信で取得する。
            // 取得できたデータはURL形式でサーバーから受け取ると仮定する。
            // 疑問に思ったがEntityやUseCaseがURLを扱う、つまりhttpに依存するのは問題ないのだろうか。
            // サンプルコードを確認すると扱っていたので問題はなさそう。
            // 問題ないというか、サーバーがURL形式で教えてくれるからURLとして扱わないとどうしようもない気がする。
            val thumbnail =
                "https://user-images.githubusercontent.com/72949030/101239894-a0290c00-372e-11eb-909f-d24a10dc543a.jpg"
            return@withContext Success(
                ThumbnailList(
                    mutableListOf(
                        Thumbnail(1, thumbnail),
                        Thumbnail(2, thumbnail),
                        Thumbnail(3, thumbnail),
                        Thumbnail(4, thumbnail),
                        Thumbnail(5, thumbnail),
                        Thumbnail(6, thumbnail),
                        Thumbnail(7, thumbnail),
                        Thumbnail(8, thumbnail),
                        Thumbnail(9, thumbnail),
                        Thumbnail(10, thumbnail),
                        Thumbnail(11, thumbnail),
                        Thumbnail(12, thumbnail),
                    )
                )
            )
        }

    private fun isNetAvailable() = true
}
