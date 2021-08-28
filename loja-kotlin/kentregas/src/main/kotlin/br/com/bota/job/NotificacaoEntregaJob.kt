package br.com.bota.job

import br.com.bota.queue.PedidoClient
import br.com.bota.service.EntregaService
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
open class NotificacaoEntregaJob @Inject constructor(
    private val entregaService: EntregaService,
    private val pedidoClient: PedidoClient
) {
    @Scheduled(fixedDelay = "30s", initialDelay = "10s")
    open fun notificarEntrega() {
        var entregas = entregaService.confirmarEntrega();
        entregas.forEach {
            it.pedidoId?.let { pedidoId -> pedidoClient.notificarPacoteEntregue(pedidoId) }
        }
    }
}
