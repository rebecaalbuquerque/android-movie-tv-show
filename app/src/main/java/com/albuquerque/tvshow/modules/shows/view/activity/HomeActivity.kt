package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.shows.adapter.CategoryListShowAdapter
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private lateinit var listShowsViewModel: ListShowsViewModel
    private lateinit var categoryShowsAdapter: CategoryListShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        progressHome.visibility = VISIBLE
        listShowsViewModel = ViewModelProviders.of(this).get(ListShowsViewModel::class.java)
        categoryShowsAdapter = CategoryListShowAdapter()

        setupAdapter()
        subscribeUI()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    private fun setupAdapter(){
        rvCategories.adapter = categoryShowsAdapter
    }

    private fun subscribeUI(){

        with(listShowsViewModel){

            getCategories().observe(this@HomeActivity, Observer { categories ->
                categories?.let {
                    progressHome.visibility = GONE
                    categoryShowsAdapter.refresh(it)
                }
            })

            onError.observe(this@HomeActivity, Observer { error ->
                error?.let {
                    progressHome.visibility = View.GONE
                    Snackbar.make(homeLayout, error, Snackbar.LENGTH_LONG).showError()
                }
            })

        }

    }

}
