package br.com.bota.queue;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

import java.util.UUID;

@RabbitClient("exchange_entrega_pedido")
public interface PedidoClient {
    @Binding("br.com.bota.entrega_to_pedido")
    void notificarPacoteEntregue(String pedidoId);
}
