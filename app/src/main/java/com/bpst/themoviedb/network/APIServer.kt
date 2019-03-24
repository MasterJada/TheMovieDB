package com.bpst.themoviedb.network

import com.bpst.themoviedb.models.MovieModel
import com.bpst.themoviedb.models.Response
import com.bpst.themoviedb.models.Result
import io.reactivex.Notification
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIServer {
    @GET("movie/changes?api_key=07d14937ab9eada9e0006d894c9f2c52")
    fun loadChanges():Observable<Response>

    @GET("movie/{id}?api_key=07d14937ab9eada9e0006d894c9f2c52")
    fun loadMovieInfo(@Path("id") movieId: Int ): Observable<MovieModel>

    companion object {
        fun buildApi():APIServer {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIServer::class.java)
        }

    }

}