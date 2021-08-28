package br.com.bota.facade

import br.com.bota.entity.Pedido
import br.com.bota.event.client.ProdutoEntregaEvent
import br.com.bota.lojalib.dto.ClienteDTO
import br.com.bota.lojalib.dto.EntregaDTO
import br.com.bota.lojalib.dto.ItemEntregaDTO
import br.com.bota.ws.highapi.ProdutoClient
import br.com.bota.ws.lowapi.ClienteClient
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.Instant

interface EntregaFacade {
    fun entregar(pedidos: List<Pedido>)
}

@Singleton
class EntregaFacadeImpl @Inject constructor(
    private val produtoEntregaEvent: ProdutoEntregaEvent,
    private val clienteClient: ClienteClient,
    private val produtoClient: ProdutoClient
) : EntregaFacade {
    override fun entregar(pedidos: List<Pedido>) {
        pedidos.forEach {
            val cliente: ClienteDTO = clienteClient.buscarClientePorId(it.clienteId!!).blockingGet()
            val produtos = it.itens?.map { itp -> produtoClient.buscarPorId(itp.produtoId).blockingFirst() }
            val itens =
                it.itens?.map { ie ->
                    ItemEntregaDTO.builder().quantidade(ie.quantidade).valor(ie.valor)
                        .produto(produtos?.filter { pr -> pr.id == ie.produtoId }
                            ?.map { pr -> pr.descricao }?.first())
                        .build()
                }?.toSet()
            val entrega = EntregaDTO.builder().pedidoId(it.id).cliente(cliente.nome).endereco(cliente.endereco)
                .valorTotal(it.sumAndGetValorTotal()).itens(itens).build()
            produtoEntregaEvent.enviarPedidoAoEntregador(Instant.now().toEpochMilli(), entrega)
        } // fim foreach
    }
}