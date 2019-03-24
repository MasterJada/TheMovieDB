package com.bpst.themoviedb

import android.widget.ImageView
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


fun ImageView.loadUrl(url: String){
    Glide.with(this).load(url).into(this)
}

operator fun  CompositeDisposable.plusAssign(disposable: Disposable){
    this.add(disposable)
}

fun<T> Observable<T>.call(): Observable<T>{
  return  this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}