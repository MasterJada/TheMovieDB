package com.bpst.themoviedb.about_movie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bpst.themoviedb.R
import com.bpst.themoviedb.loadUrl
import com.bpst.themoviedb.models.MovieModel
import kotlinx.android.synthetic.main.fragment_about_movie.*

class AboutMovieFragment : Fragment() {
    var movie: MovieModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movie = arguments?.getParcelable("movie")
        return inflater.inflate(R.layout.fragment_about_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (movie == null) {
            fragmentManager?.popBackStack()
            return
        }
        movie?.let {
            top_poster.loadUrl(it.getPosterOriginal())
            tv_title.text = it.title
            tv_subtitle.text = it.subtitle
            tv_overview.text = it.overview
        }
    }

}
