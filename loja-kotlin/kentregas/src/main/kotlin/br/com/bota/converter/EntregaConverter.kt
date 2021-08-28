package br.com.bota.converter

import br.com.bota.entity.Entrega
import br.com.bota.entity.ItemEntrega
import br.com.bota.lojalib.dto.EntregaDTO

class EntregaConverter {
    companion object {
        fun toEntrega(dto: EntregaDTO): Entrega =
            Entrega(
                pedidoId = dto.pedidoIdAsString,
                cliente = dto.cliente,
                endereco = dto.endereco,
                valorTotal = dto.valorTotalAsString,
                itens = dto.itens?.map { ItemEntrega(it.produto, it.quantidade, it.valor.toString()) }?.toTypedArray()
            )

    }
}