package br.com.bota.converter

import br.com.bota.entity.Pedido
import br.com.bota.lojalib.dto.PedidoDTO

class PedidoConverter {
    companion object {

        fun toPedidoDTO(pedido: Pedido): PedidoDTO {
            val pedidoDTO = PedidoDTO()
            pedidoDTO.id = pedido.id
            pedidoDTO.clienteId = pedido.clienteId
            pedidoDTO.pedidoRealizadoEm = pedido.pedidoRealizadoEm
            pedidoDTO.status = pedido.status
            pedidoDTO.itens = pedido.itens?.map { ItemPedidoConverter.toItemPedidoDTO(it) }
            pedidoDTO.valorTotal = pedido.sumAndGetValorTotal()
            return pedidoDTO
        }


        fun toPedido(pedidoDTO: PedidoDTO) =
            Pedido(
                pedidoDTO.id,
                pedidoDTO.pedidoRealizadoEm,
                pedidoDTO.status,
                pedidoDTO.clienteId,
                pedidoDTO.itens.map { ItemPedidoConverter.toItemPedido(it) }.toMutableList()
            )
    }
}