package br.com.bota.facade;

import br.com.bota.entity.Pedido;

import java.util.List;

public interface EntregaFacade {
    void entregar(List<Pedido> pedidos);
}
