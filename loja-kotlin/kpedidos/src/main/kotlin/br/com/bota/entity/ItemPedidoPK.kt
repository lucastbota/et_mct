package br.com.bota.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
open class ItemPedidoPK(
    open var pedido: Pedido,
    open var produtoId: UUID
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemPedidoPK

        return pedido == other.pedido && produtoId == other.produtoId
    }

    override fun hashCode(): Int {
        var result = pedido.hashCode()
        result = 31 * result + produtoId.hashCode()
        return result
    }
}