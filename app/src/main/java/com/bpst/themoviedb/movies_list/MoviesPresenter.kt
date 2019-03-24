package com.bpst.themoviedb.movies_list

class MoviesPresenter{
    private constructor()

    private val model = MoviesListModel(this)
    private var view: MoviesListView? = null
    fun setupView(view: MoviesListView){
        this.view = view
    }
    fun pushData(){
        view?.loadData(model)
        hideLoading()
    }
    fun popData(start: Int){
        showLoading()
        model.load(start = start)
    }

    fun showLoading(){
        view?.showLoading()
    }
    fun hideLoading(){
        view?.hideLoading()
    }

    fun showError(message: String){
        view?.showError(message)
    }
    companion object {
        val instance by lazy { MoviesPresenter() }
    }
}