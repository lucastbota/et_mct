package br.com.bota.facade

import br.com.bota.entity.Pedido
import br.com.bota.lojalib.dto.EstoqueDTO
import br.com.bota.lojalib.enums.PedidoStatusEnum
import br.com.bota.queue.ProdutoQueue
import br.com.bota.service.PedidoService
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.util.*
import javax.transaction.Transactional

interface PedidoFacade {
    fun criar(pedido: Pedido): Pedido
    fun findById(clienteId: UUID): List<Pedido>
    fun processarPedido()
    fun iniciarProcessoEntrega()
    fun finalizarEntrega(pedidoId: UUID)
}

@Singleton
open class PedidoFacadeImpl @Inject constructor(
    private val pedidoService: PedidoService,
    private val produtoQueue: ProdutoQueue,
    private val entregaFacade: EntregaFacade
) : PedidoFacade {
    private val log = LoggerFactory.getLogger(PedidoFacadeImpl::class.java)

    @Transactional
    override fun criar(pedido: Pedido): Pedido {
        val pedidoCriado = pedidoService.criar(pedido)
        log.info("envia")
        baixarEstoque(pedidoCriado)
        return pedidoCriado
    }

    override fun findById(clienteId: UUID): List<Pedido> = pedidoService.findBy(clienteId)

    @Transactional
    override fun processarPedido() {
        pedidoService.mudarStatus(setOf(PedidoStatusEnum.AGUARDANDO_PAGAMENTO, PedidoStatusEnum.PAGAMENTO_APROVADO))
    }

    @Transactional
    override fun iniciarProcessoEntrega() {
        val pedidos = pedidoService.obterPedidosSeparadosEntrega()
        entregaFacade.entregar(pedidos)
        pedidos::forEach{ it.status = PedidoStatusEnum.PEDIDO_ENTREGUE_TRANSPORTADORA }
    }

    @Transactional
    override fun finalizarEntrega(pedidoId: UUID) {
        pedidoService.finalizarEntrega(pedidoId)
    }

    private fun baixarEstoque(pedido: Pedido) {
        pedido.itens?.map { EstoqueDTO.builder().produtoId(it.produtoId).quantidade(it.quantidade).build() }
            ?.forEach { produtoQueue.send(it) }
    }
}