package br.com.bota.facade;

import br.com.bota.entity.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoFacade {

    Pedido criar(Pedido pedido);

    List<Pedido> findBy(UUID clienteId);

    void processarPedido();

    void iniciarProcessoEntrega();

    void finalizarEntrega(UUID pedidoId);
}
