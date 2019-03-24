package com.bpst.themoviedb.movies_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bpst.themoviedb.R
import com.bpst.themoviedb.loadUrl
import com.bpst.themoviedb.models.MovieModel


class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MovieVH>() {

    var items: List<MovieModel> = ArrayList()
    set(value) {
        val start = field.size
        field = value
        notifyItemRangeInserted(start, field.size)
    }

    private var onClick: ((MovieVH, MovieModel)->Unit)? = null

    fun setupOnClick(click: ((MovieVH, MovieModel) -> Unit)){
        onClick = click
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieVH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val item = items[position]

        holder.poster.loadUrl(item.getPosterSmall())
        holder.title.text = item.title
        holder.subTitle.text = item.subtitle
        holder.itemView.setOnClickListener {
            onClick?.invoke(holder, item)
        }
    }

    class MovieVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val subTitle: TextView = itemView.findViewById(R.id.tv_subtitle)
    }
}