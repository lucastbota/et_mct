package br.com.bota.entity

import java.io.Serializable

open class ItemEntrega(
    var produto: String,
    var quantidade: Int,
    var valor: String
) : Serializable