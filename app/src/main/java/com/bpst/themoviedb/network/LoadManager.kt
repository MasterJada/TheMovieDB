package com.bpst.themoviedb.network

import com.bpst.themoviedb.call
import com.bpst.themoviedb.models.MovieModel
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

object LoadManager {
    fun loadNext(start: Int = 0, limit: Int = 30): Observable<List<MovieModel>> {
        val api = APIServer.buildApi()
       return api.loadChanges()
            .flatMap { it.results.toObservable() }
            .filter { !it.adult }
            .skip(start.toLong())
            .takeLast(limit)
            .flatMap { api.loadMovieInfo(it.id)
                .onErrorResumeNext(Observable.just(MovieModel("", "", "","", -1))) }
            .filter { it.isValid() }
           .buffer(3)
           .call()
    }


}