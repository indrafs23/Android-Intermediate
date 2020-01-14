package com.example.moviecatalogue.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.movies.MoviesFragment
import com.example.moviecatalogue.tvShow.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MoviesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                .commit()
        }

        navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            setMode(item.itemId)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.navigation_movie -> {
                val fragment = MoviesFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                    .commit()
            }
            R.id.navigation_tvShow -> {
                val fragment = TvShowFragment()
                supportFragmentManager.beginTransaction().replace(R.id.mainActivity, fragment)
                    .commit()
            }
        }
    }
}
