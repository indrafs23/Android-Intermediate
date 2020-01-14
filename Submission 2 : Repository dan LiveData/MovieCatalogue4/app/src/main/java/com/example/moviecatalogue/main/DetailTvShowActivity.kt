package com.example.moviecatalogue.main

import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.service.ViewModelFactory
import com.example.moviecatalogue.tvShow.DetailTvShowViewModel
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import kotlinx.android.synthetic.main.activity_detail_tv_show.*

class DetailTvShowActivity : AppCompatActivity() {
    private var id: Int = 0
    private lateinit var viewModel: DetailTvShowViewModel

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
            showDetail(it)
        })
    }

    @NonNull
    private fun obtainViewModel(activity: AppCompatActivity): DetailTvShowViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailTvShowViewModel::class.java)
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
