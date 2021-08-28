package br.com.bota.service

import br.com.bota.entity.Pedido
import br.com.bota.lojalib.enums.PedidoStatusEnum
import br.com.bota.repository.PedidoRepository
import io.micronaut.transaction.annotation.ReadOnly
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.util.*
import javax.transaction.Transactional

interface PedidoService {
    fun criar(pedido: Pedido): Pedido
    fun findBy(clienteId: UUID): List<Pedido>
    fun mudarStatus(status: Set<PedidoStatusEnum>)
    fun finalizarEntrega(pedidoId: UUID)
    fun obterPedidosSeparadosEntrega(): List<Pedido>
}

@Singleton
open class PedidoServiceImpl(@Inject private val repository: PedidoRepository) : PedidoService {
    private val log = LoggerFactory.getLogger(PedidoServiceImpl::class.java)

    @Transactional
    override fun criar(pedido: Pedido): Pedido {
        pedido.status = PedidoStatusEnum.AGUARDANDO_PAGAMENTO
        pedido.itens?.forEach { it.pedido = pedido }
        return repository.save(pedido)
    }

    @ReadOnly
    override fun findBy(clienteId: UUID): List<Pedido> {
        return repository.findByClienteIdOrderById(clienteId)
    }

    @Transactional
    override fun mudarStatus(status: Set<PedidoStatusEnum>) {
        val pedidos = repository.findByStatusIn(status)
        log.info("Qtd pedidos: {}", pedidos.size)

        pedidos.forEach {
            logAntes(it)
            mudarStatus(it)
            logDepois(it)
        }
    }

    @Transactional
    override fun finalizarEntrega(pedidoId: UUID) {
        repository.findById(pedidoId).ifPresent { it.status = PedidoStatusEnum.PEDIDO_ENTREGUE }
    }

    @ReadOnly
    override fun obterPedidosSeparadosEntrega(): List<Pedido> {
        return repository.findByStatusIn(setOf(PedidoStatusEnum.PEDIDO_SEPARADO_TRANSPORTADORA))
    }

    private fun logAntes(p: Pedido) {
        log.info("Pedido {} - {}", p.id, p.status)
    }

    private fun mudarStatus(p: Pedido) {
        p.status = (p.status?.next())
    }

    private fun logDepois(p: Pedido) {
        log.info("Pedido {} - {}", p.id, p.status)
    }
}