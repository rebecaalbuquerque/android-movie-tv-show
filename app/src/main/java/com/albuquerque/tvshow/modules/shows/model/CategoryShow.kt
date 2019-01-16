package com.albuquerque.tvshow.modules.shows.model

class CategoryShow() {

    var name: String = ""
    var shows: List<Show> = listOf()

    constructor(name: String, shows: List<Show>): this(){
        this.name = name
        this.shows = shows
    }

}