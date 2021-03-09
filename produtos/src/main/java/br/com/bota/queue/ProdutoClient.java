package br.com.bota.queue;

import br.com.bota.dto.EstoqueDTO;
import br.com.bota.service.ProdutoService;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;

import javax.inject.Inject;

@RabbitListener()
public class ProdutoClient {
    @Inject
    private ProdutoService service;

    @Queue("${rabbitmq.custom.queue.name}")
    public void atualizarQuantidade(EstoqueDTO estoqueDTO) {
        service.baixarEstoque(estoqueDTO.getProdutoId(), estoqueDTO.getQuantidade());
    }
}
