package com.albuquerque.tvshow.modules.shows.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.albuquerque.tvshow.R
import com.albuquerque.tvshow.modules.shows.viewmodel.ShowViewModel
import com.albuquerque.tvshow.modules.shows.viewmodel.ShowViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.content_detail.view.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val SHOW_ID = "SHOW_ID"
    }

    private var showID = -1
    private lateinit var showViewModel: ShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showID = intent.getIntExtra(SHOW_ID, -1)

        showViewModel = ViewModelProviders.of(
                this,
                ShowViewModelFactory(showID)).get(ShowViewModel::class.java
        )

        setupToolbar()
        setupFab()
        subscribeUI()

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFab(){
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun subscribeUI() {
        with(showViewModel){

            onError.observe(this@DetailActivity, Observer {

            })

            getShow().observe(this@DetailActivity, Observer {  show ->
                show?.let {
                    toolbar_layout.title = show.name
                    text2.text = show.overview
                    Picasso.get().load(show.backdropPath).into(expandedImage)
                }
            })

        }
    }
}
