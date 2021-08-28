package br.com.bota.entity

import org.bson.Document
import org.bson.types.ObjectId

open class Entrega(
    var id: ObjectId? = null,
    var pedidoId: String? = null,
    var cliente: String? = null,
    var endereco: String? = null,
    var entregou: Boolean? = null,
    var pacoteRecebidoEm: String? = null,
    var entregaRealizadaEm: String? = null,
    var assinaturaCliente: String? = null,
    var documentoCliente: String? = null,
    var itens: Array<ItemEntrega>? = null,
    var valorTotal: String? = null
) {
    companion object {
          val ID = "_id"
          val PEDIDO_ID = "pedido_id"
          val CLIENTE = "cliente"
          val ENDERECO = "endereco"
          val ENTREGOU = "entregou"
          val ENTREGA_REALIZADA_EM = "entrega_realizada_em"
          val PACOTE_RECEBIDO_EM = "pacote_recebido_em"
          val ASSINATURA_CLIENTE = "assinatura_cliente"
          val DOCUMENTO_CLIENTE = "documento_cliente"
          val ITENS = "itens"
          val ITENS_PRODUTO = "produto"
          val ITENS_QUANTIDADE = "quantidade"
          val ITENS_VALOR = "valor"
          val VALOR_TOTAL = "valor_total"
    }

    fun toDocument(): Document {
        var document = Document()
        id.let { document.put(ID, id) }
        pedidoId.let { document.put(PEDIDO_ID, pedidoId) }
        cliente.let { document.put(CLIENTE, cliente) }
        endereco.let { document.put(ENDERECO, endereco) }
        entregou.let { document.put(ENTREGOU, entregou) }
        entregaRealizadaEm.let { document.put(ENTREGA_REALIZADA_EM, entregaRealizadaEm) }
        pacoteRecebidoEm.let { document.put(PACOTE_RECEBIDO_EM, pacoteRecebidoEm) }
        assinaturaCliente.let { document.put(ASSINATURA_CLIENTE, assinaturaCliente) }
        documentoCliente.let { document.put(DOCUMENTO_CLIENTE, documentoCliente) }
        itens.let { i ->
            document.put(ITENS, i?.map { j ->
                val itemDoc = Document()
                itemDoc.put(ITENS_PRODUTO, j.produto)
                itemDoc.put(ITENS_QUANTIDADE, j.quantidade)
                itemDoc.put(ITENS_VALOR, j.valor)
                return itemDoc
            })
        }
        valorTotal.let { document.put(VALOR_TOTAL, valorTotal) }

        return document
    }
}