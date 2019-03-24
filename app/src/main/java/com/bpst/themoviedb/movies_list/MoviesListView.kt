package com.bpst.themoviedb.movies_list

interface MoviesListView {
    fun loadData(model: MoviesListModel)
    fun showError(message: String)
    fun showLoading()
    fun hideLoading()
}