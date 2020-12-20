package com.aru_oi.cleanarchitecture.ui.comic

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aru_oi.cleanarchitecture.domain.entitiy.Comic
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import com.aru_oi.cleanarchitecture.exception.FeatureFailure
import com.aru_oi.cleanarchitecture.domain.usecase.comic.ComicInputData
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IComicPresenter
import com.aru_oi.cleanarchitecture.domain.usecase.comic.IComicUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Collections.emptyList

class ComicItemsViewModel @ViewModelInject constructor(
    private val comicUseCaseIF: IComicUseCase,
    @ApplicationContext private val context: Context,
    private val presenterIF : IComicPresenter
) : ViewModel() {

    companion object {
        private const val TAG = "ComicItemsViewModel"
    }

    private val _comic: MutableLiveData<List<ComicViewModel>> = MutableLiveData(emptyList())
    val comic: LiveData<List<ComicViewModel>> = _comic

    private val observer = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
        }
    }

    init {
        viewModelScope.launch {
            presenterIF.onResult { it.fold(::onSuccess, ::onFailure) }
        }
        comicUseCaseIF.setPresenter(presenterIF)
        // ContentProviderなど、何かを監視する処理はUseCase/Entityでやらないこと。
        // ViewModelじゃなくてもよい。
        try {
            context.contentResolver.registerContentObserver(
                Uri.parse("content://hogehoge"), true, observer
            )
        } catch (e: Exception) {

        }
    }

    fun load(keyword: String, category: Comic.Category, sort: ComicSearch.Sort) {
        viewModelScope.launch {
            // クリーンアーキテクチャのControllerが行っている処理に当たる
            // Controllerはユーザーが入力したデータをUseCaseが扱えるように
            // 抽象的なデータ（ComicInputData）に変換する。FWやDBなどに依存するデータは渡さない
            // 例え変換が必要なかったとしてもInputDataに詰めることを推奨
            comicUseCaseIF.execute(ComicInputData(keyword, category, sort))
        }
    }

    override fun onCleared() {
        context.contentResolver.unregisterContentObserver(observer)
    }

    private fun onSuccess(result: List<ComicViewModel>) {
        // live dataを更新するとBindingAdapter経由でComicAdapter#updateが呼ばれる
        _comic.value = result
    }

    private fun onFailure(failure: FeatureFailure) {
        // Failureに応じた処理を行う
        Log.d(TAG, "fail to load.")
    }
}
