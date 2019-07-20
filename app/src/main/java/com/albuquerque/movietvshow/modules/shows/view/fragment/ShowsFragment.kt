package com.albuquerque.movietvshow.modules.shows.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.extensions.setGone
import com.albuquerque.movietvshow.core.extensions.setVisible
import com.albuquerque.movietvshow.core.extensions.showError
import com.albuquerque.movietvshow.core.view.fragment.BaseFragment
import com.albuquerque.movietvshow.modules.shows.adapter.CategoryAdapter
import com.albuquerque.movietvshow.modules.shows.event.OnCategorySeeMore
import com.albuquerque.movietvshow.modules.shows.event.OnShowClicked
import com.albuquerque.movietvshow.modules.shows.view.activity.DetailActivity
import com.albuquerque.movietvshow.modules.shows.view.activity.DetailActivity.Companion.SHOW_ID
import com.albuquerque.movietvshow.modules.shows.view.activity.ListShowsActivity
import com.albuquerque.movietvshow.modules.shows.view.activity.ListShowsActivity.Companion.CATEGORY
import com.albuquerque.movietvshow.modules.shows.viewmodel.ListCategoriesViewModel
import kotlinx.android.synthetic.main.fragment_shows.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.startActivityForResult

class ShowsFragment : BaseFragment() {

    private lateinit var categoryViewModel: ListCategoriesViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryViewModel = ViewModelProviders.of(this).get(ListCategoriesViewModel::class.java)

        setupView()
        subscribeUI()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == 111){
            categoryAdapter.notifyDataSetChanged()
        }

    }

    private fun setupView(){
        categoryAdapter = CategoryAdapter()
        rvCategories.adapter = categoryAdapter
    }

    private fun subscribeUI(){

        with(categoryViewModel){

            categories.observe(this@ShowsFragment, Observer { categories ->
                categories?.let {
                    categoryAdapter.refresh(it)
                }
            })

            onError.observe(this@ShowsFragment, Observer { error ->
                error?.let {
                    rvCategories.setGone()
                    errorIcon.setVisible()
                    Snackbar.make(layoutShows, error, Snackbar.LENGTH_LONG).showError()
                }
            })

            onRequestStarted.observe(this@ShowsFragment, Observer {
                progressShows.setVisible()
            })

            onRequestFinished.observe(this@ShowsFragment, Observer {
                progressShows.setGone()
            })

        }

    }

    @Subscribe
    fun onEvent(event: OnShowClicked) {
        startActivityForResult<DetailActivity>(111, SHOW_ID to event.show.id)
    }

    @Subscribe
    fun onEvent(event: OnCategorySeeMore) {
        startActivity<ListShowsActivity>(CATEGORY to event.category.value)
    }

}
