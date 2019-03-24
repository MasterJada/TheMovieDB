package com.bpst.themoviedb.movies_list


import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.transition.Explode
import android.transition.Fade
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bpst.themoviedb.R
import com.bpst.themoviedb.about_movie.AboutMovieFragment
import kotlinx.android.synthetic.main.fragment_movies_list.*


class MoviesListFragment : Fragment(), MoviesListView {


    private val presenter = MoviesPresenter.instance
    private var start = 0

    override fun loadData(model: MoviesListModel) {
        start = model.items.size
        Handler().post {
            adapter.items = model.items.toList()
        }

    }

    private  val adapter = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setupView(this)
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(context, 3)
        rv_movies.layoutManager = layoutManager
        rv_movies.adapter = adapter

        presenter.popData(start)

        adapter.setupOnClick { movieVH, movieModel ->
            val fragment = AboutMovieFragment()
            fragment.arguments = Bundle().apply {
                putParcelable("movie", movieModel)
            }
            fragment.sharedElementEnterTransition = MovieTransitionSet()
            fragment.enterTransition = Fade()
            exitTransition = Fade()
            fragment.sharedElementReturnTransition = MovieTransitionSet()

            fragmentManager?.beginTransaction()
                ?.addToBackStack("movie")
                ?.addSharedElement(movieVH.poster, "image")
                ?.addSharedElement(movieVH.title, "title")
                ?.addSharedElement(movieVH.subTitle, "subtitle")
                ?.replace(R.id.container, fragment)
                ?.commit()
        }
        rv_movies.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy <  100) return
               val itemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                if(itemCount - lastVisible < 4){
                    presenter.popData(start)
                }

            }
        })

    }


    override fun showError(message: String) {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }.show()
    }

    override fun showLoading() {
        fl_load.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        fl_load.visibility = View.GONE
    }

}
