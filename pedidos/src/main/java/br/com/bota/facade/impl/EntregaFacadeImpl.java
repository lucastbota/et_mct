package br.com.bota.facade.impl;

import br.com.bota.dto.ClienteDTO;
import br.com.bota.dto.EntregaDTO;
import br.com.bota.dto.ItemEntregaDTO;
import br.com.bota.dto.ProdutoDTO;
import br.com.bota.entity.Pedido;
import br.com.bota.event.ProdutoEntregaEvent;
import br.com.bota.facade.EntregaFacade;
import br.com.bota.ws.high.ProdutoClient;
import br.com.bota.ws.low.ClienteClient;

import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class EntregaFacadeImpl implements EntregaFacade {
    @Inject
    private ProdutoEntregaEvent produtoEntregaEvent;
    @Inject
    private ClienteClient clienteClient;
    @Inject
    private ProdutoClient produtoClient;

    @Override
    public void entregar(List<Pedido> pedidos) {
        pedidos.forEach(p -> {
            ClienteDTO cliente = clienteClient.buscarClientePorId(p.getClienteId()).blockingGet();
            List<ProdutoDTO> produtos = p.getItens().stream().map(i -> produtoClient.buscarPorId(i.getProdutoId()).blockingFirst()).collect(Collectors.toList());

            var itens =
                    p.getItens().stream().map(i -> ItemEntregaDTO.builder().quantidade(i.getQuantidade()).valor(i.getValor()).produto(produtos.stream().filter(pr -> pr.getId().equals(i.getProdutoId())).map(pr -> pr.getDescricao()).findFirst().orElse("")).build()).collect(Collectors.toSet());
            var entrega = EntregaDTO.builder().pedidoId(p.getId()).cliente(cliente.getNome()).endereco(cliente.getEndereco())
                    .valorTotal(p.getValorTotal()).itens(itens).build();
            produtoEntregaEvent.enviarPedidoAoEntregador(Instant.now().toEpochMilli(), entrega);
        });
    }
}
