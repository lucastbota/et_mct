package br.com.bota.event.client

import br.com.bota.lojalib.dto.EntregaDTO
import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic

@KafkaClient
interface ProdutoEntregaEvent {
    @Topic("pedido_entrega")
    fun enviarPedidoAoEntregador(@KafkaKey key : Long, entregaDTO: EntregaDTO)
}