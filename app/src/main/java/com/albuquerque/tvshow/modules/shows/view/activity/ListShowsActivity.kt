package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.setOnItemClickListener
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.shows.adapter.PagedShowsAdapter
import com.albuquerque.tvshow.modules.shows.enum.TypeCategory
import com.albuquerque.tvshow.modules.shows.view.activity.DetailActivity.Companion.SHOW_ID
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModel
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModelFactory
import kotlinx.android.synthetic.main.activity_list_shows.*
import org.jetbrains.anko.startActivity

class ListShowsActivity : BaseActivity() {

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

        setupToolbar(category.value)

        setupRecyclerView()
        subscribeUI()

    }

    private fun setupToolbar(name: String) {
        supportActionBar?.title = name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView(){
        pagedAdapter = PagedShowsAdapter()
        rvShows.adapter = pagedAdapter

        rvShows.setOnItemClickListener { rv, position, _ ->

            val show = (rv.adapter as PagedShowsAdapter).getShow(position)
            startActivity<DetailActivity>(SHOW_ID to show.id)

        }
    }

    private fun subscribeUI(){
        with(listShowsViewModel){
            getShows().observe(this@ListShowsActivity, Observer { pagedList ->
                pagedList?.let { pagedAdapter.submitList(it) }
            })
        }
    }

}
