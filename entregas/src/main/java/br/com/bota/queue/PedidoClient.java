package br.com.bota.queue;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;

import java.util.UUID;

@RabbitClient("exchange_entrega_pedido")
public interface PedidoClient {
    @ContinueSpan
    @Binding("br.com.bota.entrega_to_pedido")
    void notificarPacoteEntregue(@SpanTag("pedido.baixa.estoque") String pedidoId);
}
