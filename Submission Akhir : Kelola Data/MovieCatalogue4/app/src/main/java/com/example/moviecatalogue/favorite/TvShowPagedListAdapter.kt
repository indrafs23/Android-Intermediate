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
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity

class TvShowPagedListAdapter(): PagedListAdapter<DataTvShowEntity, TvShowPagedListAdapter.ListViewHolder>(
    DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTvShowEntity>() {
            override fun areItemsTheSame(oldNote: DataTvShowEntity, newNote: DataTvShowEntity): Boolean {
                return oldNote.original_name.equals(newNote.original_name)
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldNote: DataTvShowEntity, newNote: DataTvShowEntity): Boolean {
                return oldNote == newNote
            }
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_tvshow, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull p0: ListViewHolder, position: Int) {
        val tvShow: DataTvShowEntity? = getItem(position)
        if (tvShow != null) {
            p0.filmPhoto?.let {
                Glide.with(p0.itemView.context)
                    .load(p0.context.getString(R.string.path_url_image) + tvShow.poster_path)
                    .apply(RequestOptions().override(96, 120))
                    .into(it)
            }

            p0.filmName?.text = tvShow.name
            p0.filmDescription?.text = tvShow.overview
            p0.filmDate?.text = tvShow.first_air_date
            p0.filmPopularity?.text =
                "${p0.context.getString(R.string.rating)} : ${tvShow.vote_average}"

            p0.itemView.setOnClickListener { onItemClickCallback.onItemClicked(tvShow) }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context: Context = itemView.context
        val filmName: TextView? = itemView.findViewById(R.id.tvShow_name)
        val filmDescription: TextView? = itemView.findViewById(R.id.tvShow_description)
        val filmPhoto: ImageView? = itemView.findViewById(R.id.tvShow_photo)
        val filmPopularity: TextView? = itemView.findViewById(R.id.tvShow_popularity)
        val filmDate: TextView? = itemView.findViewById(R.id.tvShow_date)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataTvShowEntity)
    }
}