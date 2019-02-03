package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.core.extensions.showError
import com.albuquerque.tvshow.core.utils.GlideApp
import com.albuquerque.tvshow.core.view.activity.BaseActivity
import com.albuquerque.tvshow.modules.shows.adapter.ImageAdapter
import com.albuquerque.tvshow.modules.shows.business.ShowsBusiness
import com.albuquerque.tvshow.modules.shows.enum.TypeImage.CHANNEL
import com.albuquerque.tvshow.modules.shows.enum.TypeImage.MEDIA_IMAGE
import com.albuquerque.tvshow.modules.shows.viewmodel.ShowViewModel
import com.albuquerque.tvshow.modules.shows.viewmodel.ShowViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : BaseActivity() {

    companion object {
        const val SHOW_ID = "SHOW_ID"
    }

    private var showID = -1
    private lateinit var showViewModel: ShowViewModel
    private lateinit var picturesAdapter: ImageAdapter
    private lateinit var channelsAdapter: ImageAdapter

    private var oldFavoriteValue = false
    private var newFavoriteValue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showProgressBar()

        showID = intent.getIntExtra(SHOW_ID, -1)

        showViewModel = ViewModelProviders.of(
                this,
                ShowViewModelFactory(showID)).get(ShowViewModel::class.java
        )

        subscribeUI()
        setupToolbar()
        setupFab()
        setupRecyclerView()

    }

    override fun onBackPressed() {

        if(showID != -1 && (oldFavoriteValue != newFavoriteValue)){
            setResult(111)
        }

        super.onBackPressed()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFab(){
        fab.setOnClickListener {
            showViewModel.handleFavoriteClick()
        }
    }

    private fun subscribeUI() {
        with(showViewModel){

            onFavorite.observe(this@DetailActivity, Observer { show ->
                show?.let {
                    if(show.isFavorite) {
                        newFavoriteValue = true
                        fab.setImageResource(R.drawable.ic_full_star)
                        Toast.makeText(this@DetailActivity, "Adicionado aos favoritos", Toast.LENGTH_LONG).show()
                    } else {
                        newFavoriteValue = false
                        fab.setImageResource(R.drawable.ic_empty_star)
                        Toast.makeText(this@DetailActivity, "Removido dos favoritos", Toast.LENGTH_LONG).show()
                    }
                }
            })

            onError.observe(this@DetailActivity, Observer { error ->
                error?.let {
                    Snackbar.make(detailLayout, it, Snackbar.LENGTH_LONG).showError()
                }
            })

            getShow().observe(this@DetailActivity, Observer {  show ->
                show?.let {
                    oldFavoriteValue = show.isFavorite
                    newFavoriteValue = show.isFavorite
                    hideProgressBar()

                    GlideApp
                            .with(this@DetailActivity)
                            .load(show.backdropPath)
                            .into(expandedImage)

                    toolbar_layout.title = show.name

                    channelsAdapter.refresh(show.networks)
                    picturesAdapter.refresh(show.images?.backdrops ?: listOf())

                    overview.text       = show.overview
                    createdBy.text      = ShowsBusiness.getDirectorsNameFormatted(show.directors)
                    firstAirDate.text   = show.firstAirDate
                    nextAirDate.text    = show.nextEpisode?.airDate ?: "N/I"
                    seasons.text        = show.seasons.toString()

                    if(show.isFavorite) {
                        fab.setImageResource(R.drawable.ic_full_star)
                    } else {
                        fab.setImageResource(R.drawable.ic_empty_star)
                    }
                }
            })

        }
    }

    private fun showProgressBar(){
        progressDetail.visibility = VISIBLE
        contentInformations.visibility = GONE
    }

    private fun hideProgressBar(){
        progressDetail.visibility = GONE
        contentInformations.visibility = VISIBLE
    }

    private fun setupRecyclerView() {
        picturesAdapter = ImageAdapter(MEDIA_IMAGE)
        rvPictures.adapter = picturesAdapter

        channelsAdapter = ImageAdapter(CHANNEL)
        rvChannels.adapter = channelsAdapter
    }
}
