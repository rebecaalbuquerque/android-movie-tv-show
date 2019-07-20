package com.albuquerque.movietvshow.modules.shows.model

import io.realm.RealmObject

open class Director: RealmObject() {

    var id: Int = 0

    var name: String = ""

}

fun List<Director>.getDirectorsNameFormatted(): String {
    return when (size) {
        0 -> "N/I"

        1 -> this@getDirectorsNameFormatted[0].name

        else -> {

            var names = ""

            for (i in 0 until size - 2)
                names += this@getDirectorsNameFormatted[i].name + ", "


            names += this@getDirectorsNameFormatted[size - 1].name

            names

        }

    }
}