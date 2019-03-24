package com.bpst.themoviedb.movies_list

import android.util.LruCache
import com.bpst.themoviedb.models.MovieModel
import com.bpst.themoviedb.network.LoadManager
import com.bpst.themoviedb.plusAssign
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.collections.HashSet

class MoviesListModel(private val presenter: MoviesPresenter) {
   var currentItemCount = 0
    val items = ArrayList<MovieModel>()
    private val disposable = CompositeDisposable()


    fun load(start: Int =0 , limit: Int = 30){
        if(start < currentItemCount){
            presenter.pushData()
            return
        }
        disposable += LoadManager.loadNext(start, limit).subscribe({
            items.addAll(it)
            currentItemCount = items.size
            presenter.pushData()
        },{
            presenter.showError(it.message ?: "Unknown error")
        })
    }



}