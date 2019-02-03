package com.albuquerque.movietvshow.modules.home.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.Menu
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.extensions.openFragment
import com.albuquerque.movietvshow.core.view.activity.BaseActivity
import com.albuquerque.movietvshow.modules.movies.view.fragment.MoviesFragment
import com.albuquerque.movietvshow.modules.shows.view.fragment.ShowsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun setupBottomNavigation() {
        // Carregando a fragment inicial
        MoviesFragment().openFragment(supportFragmentManager, R.id.container)

        navigation.setOnNavigationItemSelectedListener( BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId){

                R.id.navigation_movies -> {
                    MoviesFragment().openFragment(supportFragmentManager, R.id.container)
                    return@OnNavigationItemSelectedListener true
                }

                else -> {
                    ShowsFragment().openFragment(supportFragmentManager, R.id.container)
                    return@OnNavigationItemSelectedListener true
                }
            }
        })

    }

}
