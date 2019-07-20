package com.albuquerque.movietvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.albuquerque.movietvshow.R
import com.albuquerque.movietvshow.core.extensions.setGone
import com.albuquerque.movietvshow.core.extensions.setVisible
import com.albuquerque.movietvshow.core.extensions.showError
import com.albuquerque.movietvshow.core.utils.GlideApp
import com.albuquerque.movietvshow.core.view.activity.BaseActivity
import com.albuquerque.movietvshow.modules.shows.adapter.ImageAdapter
import com.albuquerque.movietvshow.modules.shows.enum.TypeImage.CHANNEL
import com.albuquerque.movietvshow.modules.shows.enum.TypeImage.MEDIA_IMAGE
import com.albuquerque.movietvshow.modules.shows.model.Show
import com.albuquerque.movietvshow.modules.shows.model.getDirectorsNameFormatted
import com.albuquerque.movietvshow.modules.shows.viewmodel.ShowViewModel
import com.albuquerque.movietvshow.modules.shows.viewmodel.ShowViewModelFactory
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

        showID = intent.getIntExtra(SHOW_ID, -1)

        showViewModel = ViewModelProviders.of(
                this,
                ShowViewModelFactory(showID)
        ).get(ShowViewModel::class.java)

        subscribeUI()
        setupToolbar()
        setupView()

    }

    override fun onBackPressed() {

        if(showID != -1 && (oldFavoriteValue != newFavoriteValue))
            setResult(111)

        super.onBackPressed()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView() {
        btnTentarNovamente.setOnClickListener { showViewModel.updateShow() }
    }

    private fun setupShow(show: Show){

        fab.setOnClickListener { showViewModel.handleFavoriteClick() }

        picturesAdapter = ImageAdapter(MEDIA_IMAGE)
        rvPictures.adapter = picturesAdapter

        channelsAdapter = ImageAdapter(CHANNEL)
        rvChannels.adapter = channelsAdapter

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
        createdBy.text      = show.directors.getDirectorsNameFormatted()
        firstAirDate.text   = show.firstAirDate
        nextAirDate.text    = show.nextEpisode?.airDate ?: "N/I"
        seasons.text        = show.seasons.toString()

        if(show.isFavorite) {
            fab.setImageResource(R.drawable.ic_full_star)
        } else {
            fab.setImageResource(R.drawable.ic_empty_star)
        }

    }

    private fun subscribeUI() {
        with(showViewModel){

            onSelectedFavorite.observe(this@DetailActivity, Observer {
                newFavoriteValue = true
                fab.setImageResource(R.drawable.ic_full_star)
                Toast.makeText(this@DetailActivity, "Adicionado aos favoritos", Toast.LENGTH_LONG).show()
            })

            onUnselectedFavorite.observe(this@DetailActivity, Observer {
                newFavoriteValue = false
                fab.setImageResource(R.drawable.ic_empty_star)
                Toast.makeText(this@DetailActivity, "Removido dos favoritos", Toast.LENGTH_LONG).show()
            })

            onError.observe(this@DetailActivity, Observer { error ->
                error?.let {
                    Snackbar.make(detailLayout, it, Snackbar.LENGTH_LONG).showError()
                    containerErrorDetail.setGone()
                    fab.setVisible()
                }
            })

            onErrorDB.observe(this@DetailActivity, Observer { error ->
                error?.let {
                    fab.setGone()
                    containerErrorDetail.setVisible()
                    Snackbar.make(detailLayout, it, Snackbar.LENGTH_LONG).showError()
                }
            })

            show.observe(this@DetailActivity, Observer {  show ->
                show?.let {
                    fab.setVisible()
                    containerErrorDetail.setGone()
                    setupShow(it)
                }
            })

            onRequestStarted.observe(this@DetailActivity, Observer {
                showProgressBar()
            })

            onRequestFinished.observe(this@DetailActivity, Observer {
                hideProgressBar()
            })

        }
    }

    private fun showProgressBar(){
        progressDetail.setVisible()
        contentInformations.setGone()
    }

    private fun hideProgressBar(){
        progressDetail.setGone()
        contentInformations.setVisible()
    }

}
