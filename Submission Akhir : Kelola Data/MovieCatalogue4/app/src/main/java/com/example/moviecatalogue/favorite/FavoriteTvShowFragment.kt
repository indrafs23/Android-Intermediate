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
import com.example.moviecatalogue.main.DetailTvShowActivity
import com.example.moviecatalogue.service.ViewModelDatabaseFactory
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var listTvShowAdapter : TvShowPagedListAdapter
    private val tcShowObserver =
        Observer<PagedList<DataTvShowEntity>> { tvShowList ->
            if (tvShowList != null) {
                listTvShowAdapter.submitList(tvShowList)
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        framelayout_tvShow_favorite.visibility = View.VISIBLE
        progressBar_tvShow_favorite.visibility = View.VISIBLE

        if (activity != null) {
            tvShowViewModel = obtainViewModel(activity!!)
        }

        tvShowViewModel.getAllTvShow().observe(this, tcShowObserver)

        listTvShowAdapter = TvShowPagedListAdapter()

        tvShowFavoriteRecyclerView.apply {
            framelayout_tvShow_favorite.visibility = View.GONE
            progressBar_tvShow_favorite.visibility = View.GONE

            tvShowFavoriteRecyclerView.layoutManager = LinearLayoutManager(activity)
            tvShowFavoriteRecyclerView.adapter = listTvShowAdapter

            listTvShowAdapter.setOnItemClickCallback(object :
                TvShowPagedListAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataTvShowEntity) {
                    val moveDetail = Intent(getActivity(), DetailTvShowActivity::class.java)
                    moveDetail.putExtra("id", data.id)
                    startActivity(moveDetail)
                }
            })
        }
    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): TvShowViewModel {
        val factory = ViewModelDatabaseFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel::class.java)
    }
}
