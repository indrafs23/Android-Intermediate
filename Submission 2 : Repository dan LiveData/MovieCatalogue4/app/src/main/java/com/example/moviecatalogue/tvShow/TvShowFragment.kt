package com.example.moviecatalogue.tvShow


import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.moviecatalogue.main.DetailTvShowActivity
import com.example.moviecatalogue.service.ViewModelFactory
import com.example.moviecatalogue.tvShow.dataTvShow.DataTvShowEntity
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {
    private val listTvShowAdapter = ListTvShowAdapter()
    private lateinit var viewModelTvShow: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowRecyclerView.setHasFixedSize(true)

        framelayout_fragment_tvShow.visibility = View.VISIBLE
        progressBar_fragment_tvShow.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            viewModelTvShow = obtainViewModel(activity!!)
        }

        viewModelTvShow.getTvShow().observe(this, Observer<List<DataTvShowEntity>> { tvShow ->
            Log.d("Masuk", "<>")
            tvShowRecyclerView.layoutManager = LinearLayoutManager(activity)
            listTvShowAdapter.setNewDataTvShow(tvShow)
            tvShowRecyclerView.adapter = listTvShowAdapter

            framelayout_fragment_tvShow.visibility = View.GONE
            framelayout_fragment_tvShow.visibility = View.GONE

            listTvShowAdapter.setOnItemClickCallback(object :
                ListTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataTvShowEntity) {
                    val moveDetail = Intent(getActivity(), DetailTvShowActivity::class.java)
                    moveDetail.putExtra("id", data.id)
                    startActivity(moveDetail)
                }
            })
        })
    }

    @NonNull
    private fun obtainViewModel(activity: FragmentActivity): TvShowViewModel {
        val factory = ViewModelFactory().getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel::class.java)
    }
}
