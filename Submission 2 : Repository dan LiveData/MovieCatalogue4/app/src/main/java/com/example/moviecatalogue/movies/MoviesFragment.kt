package com.example.moviecatalogue.movies


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.main.DetailMoviesActivity
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {
    private val listFilmAdapter = ListMovieAdapter()
    private lateinit var viewModelMovie: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesRecyclerView.setHasFixedSize(true)
        framelayout_fragment_film.visibility = View.VISIBLE
        progressBar_fragment_film.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            viewModelMovie = obtainViewModel(activity!!)
        }

        viewModelMovie.getMovie().observe(this, Observer<List<DataMoviesEntity>> { movies ->
            moviesRecyclerView.apply {
                moviesRecyclerView.layoutManager = LinearLayoutManager(activity)
                listFilmAdapter.setNewDataFilm(movies)
                moviesRecyclerView.adapter = listFilmAdapter

                framelayout_fragment_film.visibility = View.GONE
                progressBar_fragment_film.visibility = View.GONE

                listFilmAdapter.setOnItemClickCallback(object :
                    ListMovieAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: DataMoviesEntity) {
                        val moveDetail = Intent(getActivity(), DetailMoviesActivity::class.java)
                        moveDetail.putExtra("id", data.id)
                        startActivity(moveDetail)
                    }
                })
            }
        })
    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): MoviesViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MoviesViewModel::class.java)
    }
}
