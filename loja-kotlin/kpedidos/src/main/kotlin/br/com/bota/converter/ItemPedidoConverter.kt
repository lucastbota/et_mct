package br.com.bota.converter

import br.com.bota.entity.ItemPedido
import br.com.bota.entity.Pedido
import br.com.bota.lojalib.dto.ItemPedidoDTO

class ItemPedidoConverter {
    companion object {
        fun toItemPedidoDTO(itemPedido: ItemPedido) =
            ItemPedidoDTO(itemPedido.pedido.id, itemPedido.produtoId, itemPedido.quantidade, itemPedido.valor)

        fun toItemPedido(itemPedidoDTO: ItemPedidoDTO) = ItemPedido(
            Pedido(itemPedidoDTO.pedidoId),
            itemPedidoDTO.produtoId,
            itemPedidoDTO.quantidade,
            itemPedidoDTO.valor
        )
    }
}