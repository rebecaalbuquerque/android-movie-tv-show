package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.modules.shows.adapter.PagedShowsAdapter
import com.albuquerque.tvshow.modules.shows.enum.TypeCategory
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModel
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModelFactory
import kotlinx.android.synthetic.main.activity_list_shows.*

class ListShowsActivity : AppCompatActivity() {

    companion object {
        const val CATEGORY = "CATEGORY"
    }

    private lateinit var category: TypeCategory
    private lateinit var listShowsViewModel: ListShowsViewModel
    private lateinit var pagedAdapter: PagedShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_shows)

        category = TypeCategory.getByValue(intent.getStringExtra(CATEGORY))!!

        listShowsViewModel = ViewModelProviders.of(
                this,
                ListShowsViewModelFactory(category)).get(ListShowsViewModel::class.java
        )

        setupRecyclerView()
        subscribeUI()

    }

    private fun setupRecyclerView(){
        pagedAdapter = PagedShowsAdapter()
        rvShows.adapter = pagedAdapter
    }

    private fun subscribeUI(){
        with(listShowsViewModel){
            getShows().observe(this@ListShowsActivity, Observer { pagedList ->
                pagedList?.let { pagedAdapter.submitList(it) }
            })
        }
    }
}
