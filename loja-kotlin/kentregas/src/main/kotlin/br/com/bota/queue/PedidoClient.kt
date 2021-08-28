package br.com.bota.queue

import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag

@RabbitClient("exchange_entrega_pedido")
interface PedidoClient {
    @ContinueSpan
    @Binding("br.com.bota.entrega_to_pedido")
    fun notificarPacoteEntregue(@SpanTag("pedido.baixa.estoque") pedidoId: String)
}