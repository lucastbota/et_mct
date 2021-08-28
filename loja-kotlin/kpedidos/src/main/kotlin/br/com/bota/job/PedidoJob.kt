package br.com.bota.job

import br.com.bota.facade.PedidoFacade
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Singleton
open class PedidoJob @Inject constructor(private val pedidoFacade: PedidoFacade) {
    private val log = LoggerFactory.getLogger(PedidoJob::class.java)
    //colocar o modificador de acesso private, fará com que o job não seja executado.
    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    open fun processarPagamento(){
        log.info("Job iniciado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()))
        pedidoFacade.processarPedido();
        log.info("Job finalizado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()))
    }

    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    open fun processarEntrega() {
        log.info("Job iniciado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
        pedidoFacade.iniciarProcessoEntrega();
        log.info("Job finalizado... {}", DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()));
    }
}