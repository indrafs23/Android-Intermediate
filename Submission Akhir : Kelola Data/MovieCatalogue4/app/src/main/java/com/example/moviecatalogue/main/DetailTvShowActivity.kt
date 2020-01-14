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
import com.example.moviecatalogue.favorite.TvShowViewModel
import com.example.moviecatalogue.service.ViewModelDatabaseFactory
import com.example.moviecatalogue.service.ViewModelFactory
import com.example.moviecatalogue.tvShow.DetailTvShowViewModel
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import com.example.moviecatalogue2.database.TvShowDatabase
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var data: DataTvShowEntity
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var tvShowViewModel: TvShowViewModel
    private var tvShowDatabase: TvShowDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)

        framelayout_detail_tvShow.visibility = View.VISIBLE
        progressBar_detail_tvShow.visibility = View.VISIBLE

        addBack()
        changeName(getString(R.string.tv_show))
        id = intent.getIntExtra("id", 0)

        viewModel = obtainViewModel(this)
        viewModel.id = id

        viewModel.getTvShow().observe(this, Observer {
            data = it
            showDetail(it)
        })

        tvShowViewModel = obtainViewDatabaseModel(this)

        btn_tvShow_set_favorite.setOnClickListener {
            if (tvShowViewModel.getAllTvShow().value == null) {
                tvShowViewModel.insert(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addTvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else if (tvShowViewModel.searchId(data.id) == null) {
                tvShowViewModel.insert(data)
                val toast = Toast.makeText(
                    this,
                    getString(R.string.addTvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val toast = Toast.makeText(
                    this,
                    "${data.original_name} " + getString(R.string.selectMoviesTvShow) ,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        btn_tvShow_remove_from_favorite.setOnClickListener {
            val dialog: AlertDialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle(this.getString(R.string.title_alert_tvShow))
            builder.setMessage(this.getString(R.string.body_alert_tvShow))

            builder.setPositiveButton(this.getString(R.string.yes)) { dialog, which ->
                tvShowViewModel.delete(data)
                val toast = Toast.makeText(
                    this,
                    this.getString(R.string.toast_tvShow),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
            builder.setNegativeButton(this.getString(R.string.no)) { dialog, which ->

            }
            dialog = builder.create()
            dialog.show()
        }
    }

    @NonNull
    private fun obtainViewModel(activity: AppCompatActivity): DetailTvShowViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailTvShowViewModel::class.java)
    }

    @NonNull
    private fun obtainViewDatabaseModel(activity: AppCompatActivity): TvShowViewModel {
        val factory = ViewModelDatabaseFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel::class.java)
    }

    private fun addBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetail(tvShow: DataTvShowEntity) {
        detail_TvShow_name.setText(tvShow.name)

        detail_TvShow_date.setText(Html.fromHtml("<b>${this.getString(R.string.date)}</b> <br> ${tvShow.first_air_date}"))

        detail_TvShow_popularity.setText(Html.fromHtml("<b>${this.getString(R.string.rating)}</b> <br> ${tvShow.vote_average}"))

        detail_TvShow_description.setText(Html.fromHtml("<b>${this.getString(R.string.overview)}</b> <br> ${tvShow.overview}"))

        detail_TvShow_creator.setText(Html.fromHtml("<b>${this.getString(R.string.popularity)}</b> <br> ${tvShow.popularity}"))

        Glide.with(this)
            .load(this.getString(R.string.path_url_image) + tvShow.poster_path)
            .into(detail_TvShow_photo)

        framelayout_detail_tvShow.visibility = View.GONE
        progressBar_detail_tvShow.visibility = View.GONE

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
