package com.aru_oi.cleanarchitecture.ui.comic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.aru_oi.cleanarchitecture.R
import dagger.hilt.android.AndroidEntryPoint

import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aru_oi.cleanarchitecture.databinding.ComicBinding
import com.aru_oi.cleanarchitecture.domain.entitiy.Comic
import com.aru_oi.cleanarchitecture.domain.entitiy.ComicSearch
import kotlinx.coroutines.cancel

@AndroidEntryPoint
class ComicFragment : Fragment(R.layout.comic) {
    companion object {
        private const val TAG = "ComicFragment"
    }

    private val comicItemsModel: ComicItemsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ComicAdapter()
        val bind = DataBindingUtil.bind<ComicBinding>(view)
        adapter.onItemClickListener = { vm ->
            Log.d(TAG, "${vm.item.title} is clicked.")
        }
        bind?.run {
            comicList.layoutManager = LinearLayoutManager(requireContext())
            comicList.setHasFixedSize(true)
            comicList.adapter = adapter
            vm = comicItemsModel
            lifecycleOwner = this@ComicFragment
            btn.setOnClickListener {
                // キーワード、カテゴリーをソート順序をユーザーが入力したと仮定する
                comicItemsModel.load("keyword", Comic.Category.ALL, ComicSearch.Sort.NEW)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        comicItemsModel.viewModelScope.cancel()
    }
}
