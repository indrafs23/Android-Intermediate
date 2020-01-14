package com.example.moviecatalogue.favorite


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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.main.DetailMoviesActivity
import com.example.moviecatalogue.movies.dataMovies.DataMoviesEntity
import com.example.moviecatalogue.service.ViewModelDatabaseFactory
import kotlinx.android.synthetic.main.fragment_favorite_movies.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteMoviesFragment : Fragment() {
    private lateinit var listFilmAdapter : MoviesPagedListAdapter
    private lateinit var movieViewModel: MovieViewModel
    private val movieObserver =
        Observer<PagedList<DataMoviesEntity>> { MovieList ->
            if (MovieList != null) {
                listFilmAdapter.submitList(MovieList)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        framelayout_movies_favorite.visibility = View.VISIBLE
        progressBar_movies_favorite.visibility = View.VISIBLE

        if (activity != null) {
            movieViewModel = obtainViewModel(activity!!)
        }

        movieViewModel.getAllMovie().observe(this, movieObserver)

        listFilmAdapter = MoviesPagedListAdapter()

        moviesFavoriteRecyclerView.apply {
            framelayout_movies_favorite.visibility = View.GONE
            progressBar_movies_favorite.visibility = View.GONE

            moviesFavoriteRecyclerView.layoutManager = LinearLayoutManager(activity)
            moviesFavoriteRecyclerView.adapter = listFilmAdapter
            listFilmAdapter.setOnItemClickCallback(object : MoviesPagedListAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataMoviesEntity) {
                    val moveDetail = Intent(getActivity(), DetailMoviesActivity::class.java)
                    moveDetail.putExtra("id", data.id)
                    startActivity(moveDetail)
                }
            })
        }

    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): MovieViewModel {
        val factory = ViewModelDatabaseFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
    }

}
