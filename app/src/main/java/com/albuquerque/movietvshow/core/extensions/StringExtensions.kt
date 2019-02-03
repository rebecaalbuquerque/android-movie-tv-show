package com.albuquerque.movietvshow.core.extensions

fun String.toBrazilianDate(): String{
    val data = this.split("-")

    return if(data.isNotEmpty())
        "${data[2]}/${data[1]}/${data[0]}"
    else
        ""

}