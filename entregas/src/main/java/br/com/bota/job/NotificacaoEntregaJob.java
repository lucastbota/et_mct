package br.com.bota.job;

import br.com.bota.queue.PedidoClient;
import br.com.bota.service.EntregaService;
import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class NotificacaoEntregaJob {
    @Inject
    private EntregaService service;
    @Inject
    private PedidoClient pedidoClient;

    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    void notificarEntrega() {
        var entregas = service.confirmarEntrega();
        entregas.parallelStream().forEach(e -> pedidoClient.notificarPacoteEntregue(e.getPedidoId()));
    }
}
