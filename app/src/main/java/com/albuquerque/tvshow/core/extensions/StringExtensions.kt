package com.albuquerque.tvshow.core.extensions

fun String.toBrazilianDate(): String{
    val data = this.split("-")

    return if(data.isNotEmpty())
        "${data[2]}/${data[1]}/${data[0]}"
    else
        ""

}