package br.com.bota.queue

import br.com.bota.lojalib.dto.EstoqueDTO
import br.com.bota.service.ProdutoService
import io.micronaut.rabbitmq.annotation.Queue
import io.micronaut.rabbitmq.annotation.RabbitListener
import jakarta.inject.Inject

@RabbitListener
class ProdutoQueueListener @Inject constructor(val produtoService: ProdutoService){

    @Queue("\${rabbitmq.custom.queue.name}")
    open fun atualizarQuantidade(estoqueDTO: EstoqueDTO) {
        produtoService.baixarEstoque(estoqueDTO.getProdutoId(), estoqueDTO.getQuantidade())
    }
}