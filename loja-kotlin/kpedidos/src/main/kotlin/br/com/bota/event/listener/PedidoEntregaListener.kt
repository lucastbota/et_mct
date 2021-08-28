package br.com.bota.event.listener

import br.com.bota.facade.PedidoFacade
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener
import jakarta.inject.Inject
import java.util.*

@RabbitListener
class PedidoEntregaListener @Inject constructor(private val facade: PedidoFacade) {
    @Queue("finalizar_entrega")
    fun finalizarEntrega(pedidoId: UUID) = facade.finalizarEntrega(pedidoId)
}
