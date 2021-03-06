package com.example.moviecatalogue.favorite

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogue.R

class FragmentFavoriteAdapter(fm: FragmentManager, private val context: Context) :
    FragmentPagerAdapter(fm) {
    private val pages = listOf(
        FavoriteMoviesFragment(),
        FavoriteTvShowFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.movies)
            1 -> context.getString(R.string.tv_show)
            else -> ""
        }
    }
}