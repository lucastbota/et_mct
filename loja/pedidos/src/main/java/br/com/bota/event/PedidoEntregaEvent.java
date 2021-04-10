package br.com.bota.event;

import br.com.bota.facade.PedidoFacade;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.UUID;

@Slf4j
@RabbitListener
public class PedidoEntregaEvent {
    @Inject
    private PedidoFacade facade;

    @Queue("finalizar_entrega")
    public void finalizarEntrega(UUID pedidoId) {
        facade.finalizarEntrega(pedidoId);
    }
}