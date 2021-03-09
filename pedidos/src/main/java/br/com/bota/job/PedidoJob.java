package br.com.bota.job;

import br.com.bota.facade.PedidoFacade;
import io.micronaut.scheduling.annotation.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Singleton
public class PedidoJob {
    @Inject
    private PedidoFacade pedidoFacade;

    //colocar o modificador de acesso private, fará com que o job não seja executado.
    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    void processarPagamento() {
        log.info("Job iniciado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
        pedidoFacade.processarPedido();
        log.info("Job finalizado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
    }

    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    void processarEntrega() {
        log.info("Job iniciado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
        pedidoFacade.iniciarProcessoEntrega();
        log.info("Job finalizado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
    }
}
