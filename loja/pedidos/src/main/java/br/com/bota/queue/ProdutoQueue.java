package br.com.bota.queue;

import br.com.bota.dto.EstoqueDTO;
import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

//value na annotation rabbitClient, é o exchange.
@RabbitClient("exchange_baixa_estoque")
public interface ProdutoQueue {
    //route key
    @Binding("br.com.bota.produto")
    void send(EstoqueDTO estoqueDTO);
}