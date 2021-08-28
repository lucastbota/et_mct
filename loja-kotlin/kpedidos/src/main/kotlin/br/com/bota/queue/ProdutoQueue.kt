package br.com.bota.queue

import br.com.bota.lojalib.dto.EstoqueDTO
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient

//value na annotation rabbitClient, é o exchange.
@RabbitClient("exchange_baixa_estoque")
interface ProdutoQueue {
    //route key
    @Binding("br.com.bota.produto")
    fun send(estoqueDTO: EstoqueDTO)
}