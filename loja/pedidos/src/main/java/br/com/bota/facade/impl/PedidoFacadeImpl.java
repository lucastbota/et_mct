package br.com.bota.facade.impl;

import br.com.bota.dto.EstoqueDTO;
import br.com.bota.entity.Pedido;
import br.com.bota.entity.enums.PedidoStatusEnum;
import br.com.bota.facade.EntregaFacade;
import br.com.bota.facade.PedidoFacade;
import br.com.bota.queue.ProdutoQueue;
import br.com.bota.service.PedidoService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Transactional
public class PedidoFacadeImpl implements PedidoFacade {
    @Inject
    private PedidoService pedidoService;
    @Inject
    private ProdutoQueue produtoQueue;
    @Inject
    private EntregaFacade entregaFacade;

    @Override
    public void processarPedido() {
        pedidoService.mudarStatus(Set.of(PedidoStatusEnum.AGUARDANDO_PAGAMENTO, PedidoStatusEnum.PAGAMENTO_APROVADO));
    }

    @Override
    public void iniciarProcessoEntrega() {
        var pedidos = pedidoService.obterPedidosSeparadosEntrega();
        entregaFacade.entregar(pedidos);
        pedidos.forEach(p -> p.setStatus(PedidoStatusEnum.PEDIDO_ENTREGUE_TRANSPORTADORA));
    }

    @Override
    public Pedido criar(Pedido pedido) {
        var pedidoCriado = pedidoService.criar(pedido);
        log.info("envia");
        baixarEstoque(pedidoCriado);
        return pedidoCriado;
    }

    @Override
    public List<Pedido> findBy(UUID clienteId) {
        return pedidoService.findBy(clienteId);
    }

    @Override
    public void finalizarEntrega(UUID pedidoId) {
        pedidoService.finalizarEntrega(pedidoId);
    }

    private void baixarEstoque(Pedido pedido) {
        if (pedido.getItens() != null) {
            pedido.getItens().stream().map(i -> EstoqueDTO.builder().produtoId(i.getProdutoId()).quantidade(i.getQuantidade()).build()).forEach(produtoQueue::send);
        }
    }
}
