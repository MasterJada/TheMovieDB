package com.bpst.themoviedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bpst.themoviedb.models.MovieModel
import com.bpst.themoviedb.movies_list.MoviesListFragment
import com.bpst.themoviedb.network.APIServer
import com.bpst.themoviedb.network.LoadManager
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MoviesListFragment())
            .commit()
    }
}
