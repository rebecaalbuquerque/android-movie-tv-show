package com.albuquerque.tvshow.modules.shows.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast

import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.setOnItemClickListener
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.modules.shows.adapter.CategoryListShowAdapter
import com.albuquerque.tvshow.modules.shows.adapter.MediaAdapter
import com.albuquerque.tvshow.modules.shows.view.activity.DetailActivity
import com.albuquerque.tvshow.modules.shows.view.holder.CategoryListShowViewHolder
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModel
import kotlinx.android.synthetic.main.fragment_shows.*
import org.jetbrains.anko.support.v4.startActivity

class ShowsFragment : Fragment() {

    private lateinit var listShowsViewModel: ListShowsViewModel
    private lateinit var categoryShowsAdapter: CategoryListShowAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressShows.visibility = VISIBLE
        listShowsViewModel = ViewModelProviders.of(this).get(ListShowsViewModel::class.java)

        setupAdapter()
        setupRecyclerView()
        subscribeUI()

        //startActivity<DetailActivity>()

    }

    private fun setupRecyclerView(){


    }

    private fun setupAdapter(){
        categoryShowsAdapter = CategoryListShowAdapter()
        rvCategories.adapter = categoryShowsAdapter
    }

    private fun subscribeUI(){

        with(listShowsViewModel){

            getCategories().observe(this@ShowsFragment, Observer { categories ->
                categories?.let {
                    progressShows.visibility = GONE
                    categoryShowsAdapter.refresh(it)
                }
            })

            onError.observe(this@ShowsFragment, Observer { error ->
                error?.let {
                    progressShows.visibility = View.GONE
                    Snackbar.make(layoutShows, error, Snackbar.LENGTH_LONG).showError()
                }
            })

        }

    }

}
