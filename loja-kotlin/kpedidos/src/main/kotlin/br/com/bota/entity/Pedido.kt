package br.com.bota.entity

import br.com.bota.entity.listener.PedidoListener
import br.com.bota.lojalib.enums.PedidoStatusEnum
import io.micronaut.core.annotation.Introspected
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid

@Entity
@Table(name = "pedidos")
@EntityListeners(PedidoListener::class)
@Introspected
open class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id", columnDefinition = "BINARY(16)")
    open var id: UUID? = null,
    @Column(name = "pedido_realizado_em", nullable = false)
    open var pedidoRealizadoEm: LocalDateTime? = null,
    @Column(name = "pedido_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    open var status: PedidoStatusEnum?=null,
    @Column(name = "cliente_id", columnDefinition = "BINARY(16)")
    open var clienteId: UUID?=null,
    @Valid
    @OneToMany(mappedBy = "pedido", cascade = [CascadeType.ALL])
    open var itens: MutableList<ItemPedido>?=null
) : Serializable {

    fun sumAndGetValorTotal(): BigDecimal {
        val slist = itens?: emptyList()
        return slist.sumOf { it.valor.multiply(BigDecimal(it.quantidade)) }
    }
}