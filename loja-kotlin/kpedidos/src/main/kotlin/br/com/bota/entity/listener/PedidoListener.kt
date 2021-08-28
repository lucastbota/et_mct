package br.com.bota.entity.listener

import br.com.bota.entity.Pedido
import java.time.LocalDateTime
import javax.persistence.PrePersist

open class PedidoListener {
    @PrePersist
    open fun onSave(pedido: Pedido) {
        pedido.pedidoRealizadoEm = LocalDateTime.now()
    }
}