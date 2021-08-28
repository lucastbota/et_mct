package br.com.bota.entity

import io.micronaut.core.annotation.Introspected
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "pedido_itens")
@IdClass(ItemPedidoPK::class)
@Introspected
open class ItemPedido(
    @Id
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    open var pedido: Pedido,
    @Id
    @Column(name = "produto_id")
    open var produtoId: UUID,
    @Column(name = "quantidade_item")
    open var quantidade: Int,
    @Column(name = "valor")
    open var valor: BigDecimal
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ItemPedido
        return pedido == other.pedido && produtoId == other.produtoId
    }

    override fun hashCode(): Int {
        var result = pedido.hashCode()
        result = 31 * result + produtoId.hashCode()
        return result
    }
}