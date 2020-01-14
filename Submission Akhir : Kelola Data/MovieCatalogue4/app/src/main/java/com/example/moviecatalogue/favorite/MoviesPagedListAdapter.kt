package com.example.moviecatalogue.favorite

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity


class MoviesPagedListAdapter(): PagedListAdapter<DataMoviesEntity, MoviesPagedListAdapter.ListViewHolder>(
    DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataMoviesEntity>() {
            override fun areItemsTheSame(oldNote: DataMoviesEntity, newNote: DataMoviesEntity): Boolean {
                return oldNote.original_title.equals(newNote.original_title)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: DataMoviesEntity, newNote: DataMoviesEntity): Boolean {
                return oldNote == newNote
            }
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull p0: ListViewHolder, position: Int) {
        val movie: DataMoviesEntity? = getItem(position)
        if (movie != null) {
            p0.filmPhoto?.let {
                Glide.with(p0.itemView.context)
                    .load(p0.context.getString(R.string.path_url_image) + movie.poster_path)
                    .apply(RequestOptions().override(96, 120))
                    .into(it)
            }

            p0.filmName?.text = movie.title
            p0.filmDescription?.text = movie.overview
            p0.filmDate?.text = movie.release_date
            p0.filmPopularity?.text = "${p0.context.getString(R.string.rating)} : ${movie.vote_average}"

            p0.itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie) }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val filmName: TextView? = itemView.findViewById(R.id.film_name)
        val filmDescription: TextView? = itemView.findViewById(R.id.film_description)
        val filmPhoto: ImageView? = itemView.findViewById(R.id.film_photo)
        val filmPopularity: TextView? = itemView.findViewById(R.id.film_popularity)
        val filmDate: TextView? = itemView.findViewById(R.id.film_date)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataMoviesEntity)
    }
}