package com.bpst.themoviedb.about_movie

class AboutPresenter{

    var view: AboutView? =null
    var model:AboutModel = AboutModel()

    fun setupView(view: AboutView) {
        this.view = view
    }

    fun popData(){

    }

    companion object {
        val instance by lazy { AboutPresenter() }
    }

}