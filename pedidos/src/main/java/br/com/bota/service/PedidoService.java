package br.com.bota.service;

import br.com.bota.entity.Pedido;
import br.com.bota.entity.enums.PedidoStatusEnum;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PedidoService {
    Pedido criar(Pedido pedido);

    List<Pedido> findBy(UUID clienteId);

    void mudarStatus(Set<PedidoStatusEnum> status);

    void finalizarEntrega(UUID pedidoId);

    List<Pedido> obterPedidosSeparadosEntrega();
}
