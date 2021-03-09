package br.com.bota.entity.listener;

import br.com.bota.entity.Pedido;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class PedidoListener {
    @PrePersist
    public void onSave(Pedido order) {
        order.setPedidoRealizadoEm(LocalDateTime.now());
    }
}
