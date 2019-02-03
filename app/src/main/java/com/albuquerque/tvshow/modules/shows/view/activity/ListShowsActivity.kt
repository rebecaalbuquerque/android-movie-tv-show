package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.setOnItemClickListener
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.core.utils.ErrorUtils
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.shows.adapter.PagedShowsAdapter
import com.albuquerque.tvshow.modules.shows.enum.TypeCategory
import com.albuquerque.tvshow.modules.shows.event.OnErrorPagedList
import com.albuquerque.tvshow.modules.shows.view.activity.DetailActivity.Companion.SHOW_ID
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModel
import com.albuquerque.tvshow.modules.shows.viewmodel.ListShowsViewModelFactory
import kotlinx.android.synthetic.main.activity_list_shows.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.startActivity

class ListShowsActivity : BaseActivity() {

    companion object {
        const val CATEGORY = "CATEGORY"
    }

    private val bus by lazy { EventBus.getDefault() }

    private lateinit var category: TypeCategory
    private lateinit var listShowsViewModel: ListShowsViewModel
    private lateinit var pagedAdapter: PagedShowsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_shows)

        invalidateOptionsMenu()
        registerEventBus()

        category = TypeCategory.getByValue(intent.getStringExtra(CATEGORY))!!

        listShowsViewModel = ViewModelProviders.of(
                this,
                ListShowsViewModelFactory(category)).get(ListShowsViewModel::class.java
        )

        setupToolbar(category.value)

        setupRecyclerView()
        subscribeUI()

    }

    override fun onStart() {
        super.onStart()
        registerEventBus()
    }

    override fun onStop() {
        super.onStop()
        unregisterEventBus()
    }

    private fun registerEventBus() {
        if (!bus.isRegistered(this)) {
            bus.register(this)
        }
    }

    private fun unregisterEventBus() {
        if (bus.isRegistered(this)) {
            bus.unregister(this)
        }
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

            onError.observe(this@ListShowsActivity, Observer {  error ->
                error?.let {
                    Snackbar.make(layoutPagedListShow, it, Snackbar.LENGTH_LONG).showError()
                }
            })
        }
    }

    @Subscribe
    fun onEvent(event: OnErrorPagedList) {
        listShowsViewModel.onError.value = ErrorUtils.geErrorMessage(event.error) ?: "Error pagedListShows"
    }

}
