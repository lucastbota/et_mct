package br.com.bota.event;

import br.com.bota.dto.EntregaDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface ProdutoEntregaEvent {

    @Topic("pedido_entrega")
    void enviarPedidoAoEntregador(@KafkaKey long key, EntregaDTO entregaDTO);
}
