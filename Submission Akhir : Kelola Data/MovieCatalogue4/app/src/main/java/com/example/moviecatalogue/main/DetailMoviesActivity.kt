package com.example.moviecatalogue.main

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.favorite.MovieViewModel
import com.example.moviecatalogue.movies.DetailMoviesViewModel
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ViewModelDatabaseFactory
import com.example.moviecatalogue.service.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movies.*


class DetailMoviesActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var viewModel: DetailMoviesViewModel
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var data: DataMoviesEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        framelayout_detail_film.visibility = View.VISIBLE
        progressBar_detail_film.visibility = View.VISIBLE

        addBack()
        changeName(getString(R.string.movies))
        id = intent.getIntExtra("id", 0)

        viewModel = obtainViewModel(this)
        viewModel.id = id

        viewModel.getMovie().observe(this, Observer {
            data = it
            showDetail(it)
        })

        movieViewModel = obtainViewDatabaseModel(this)

        btn_film_set_favorite.setOnClickListener {
            if (movieViewModel.getAllMovie().value == null) {
                movieViewModel.insert(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addMovie),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else if (movieViewModel.searchId(data.id) == null) {
                movieViewModel.insert(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addMovie),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    this,
                    "${data.original_title} " + getString(R.string.selectMoviesTvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        btn_film_remove_from_favorite.setOnClickListener {
            val dialog: AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle(this.getString(R.string.title_alert_movie))
            builder.setMessage(this.getString(R.string.body_alert_movie))

            builder.setPositiveButton(this.getString(R.string.yes)) { dialog, which ->
                movieViewModel.delete(data)
                val toast = Toast.makeText(
                    this,
                    this.getString(R.string.toast_movie),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            builder.setNegativeButton(this.getString(R.string.no)) { dialog, which ->
                //
            }

            dialog = builder.create()
            dialog.show()
        }
    }

    @NonNull
    private fun obtainViewModel(activity: AppCompatActivity): DetailMoviesViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailMoviesViewModel::class.java)
    }

    @NonNull
    private fun obtainViewDatabaseModel(activity: AppCompatActivity): MovieViewModel {
        val factory = ViewModelDatabaseFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail(movies: DataMoviesEntity) {
        detail_film_name.setText(movies.title)

        detail_film_date.setText(Html.fromHtml("<b>${this.getString(R.string.date)} </b> <br> ${movies.release_date}"))

        detail_film_popularity.setText(Html.fromHtml("<b>${this.getString(R.string.rating)} </b> <br> ${movies.vote_average}"))

        detail_film_description.setText(Html.fromHtml("<b>${this.getString(R.string.overview)} </b> <br> ${movies.overview}"))

        detail_film_director.setText(Html.fromHtml("<b>${this.getString(R.string.vote_count)}</b> <br> ${movies.vote_count}"))

        Glide.with(this)
            .load(this.getString(R.string.path_url_image) + movies.poster_path)
            .into(detail_film_photo)

        framelayout_detail_film.visibility = View.GONE
        progressBar_detail_film.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when (itemId) {
            android.R.id.home -> {
                finish()
            }
        }
    }

    private fun changeName(title: String) {
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
