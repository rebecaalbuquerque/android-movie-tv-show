package com.albuquerque.tvshow.modules.shows.enum

enum class TypeCategory(val value: String) {
    FAVORITAS("Favoritas"),
    EM_EXIBICAO("Em exibição hoje"),
    POPULARES("Populares"),
    MELHORES_AVALIADAS("Melhores avaliadas");

    companion object {
        fun getByValue(s: String): TypeCategory?{
            val teste = values().find {
                it.value == s
            }
            return teste
        }
    }


}