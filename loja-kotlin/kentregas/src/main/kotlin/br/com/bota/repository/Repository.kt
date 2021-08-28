package br.com.bota.repository

import br.com.bota.entity.Entrega
import br.com.bota.entity.ItemEntrega
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import io.micronaut.context.annotation.Value
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.bson.Document
import org.slf4j.LoggerFactory

interface Repository {
    fun salvar(entrega: Entrega)
    fun entregar(entrega: Entrega): Entrega
    fun getPacotesNaoEntregues(): List<Entrega>
}

@Singleton
class MongoRepository(
    @Value("\${mongodb.collectionName}") private val collectionName: String,
    @Value("\${mongodb.databaseName}") private val databaseName: String,
    @Inject private val mongoClient: MongoClient
) : Repository {
    val logger = LoggerFactory.getLogger(MongoRepository::class.java)
    override fun salvar(entrega: Entrega) {
        getCollection().insertOne(entrega.toDocument())
    }

    override fun entregar(entrega: Entrega): Entrega {
        val count = getCollection().updateOne(
            Filters.eq(Entrega.ID, entrega.id), Updates.combine(
                Updates.set(Entrega.ENTREGOU, entrega.entregou),
                Updates.set(Entrega.ENTREGA_REALIZADA_EM, entrega.entregaRealizadaEm),
                Updates.set(Entrega.ASSINATURA_CLIENTE, entrega.assinaturaCliente),
                Updates.set(Entrega.DOCUMENTO_CLIENTE, entrega.documentoCliente)
            )
        ).modifiedCount
        logger.info("Modified: {}", count)
        return entrega
    }

    override fun getPacotesNaoEntregues(): List<Entrega> {
        val bsonFilter = Filters.eq("entregou", false)
        val result: FindIterable<Document> = getCollection()
            .find(bsonFilter)
        return result.into(arrayListOf()).map {
            Entrega(
                id = it.getObjectId(Entrega.ID), pedidoId = it.getString(Entrega.PEDIDO_ID),
                entregou = it.getBoolean(Entrega.ENTREGOU),
                endereco = it.getString(Entrega.ENDERECO),
                entregaRealizadaEm = it.getString(Entrega.ENTREGA_REALIZADA_EM),
                pacoteRecebidoEm = it.getString(Entrega.PACOTE_RECEBIDO_EM),
                assinaturaCliente = it.getString(Entrega.ASSINATURA_CLIENTE),
                documentoCliente = it.getString(Entrega.DOCUMENTO_CLIENTE),
                valorTotal = it.getString(Entrega.VALOR_TOTAL),
                itens = it.getList(Entrega.ITENS, Document::class.java).map { ie ->
                    ItemEntrega(
                        produto = ie.getString(Entrega.ITENS_PRODUTO),
                        quantidade = ie.getInteger(Entrega.ITENS_QUANTIDADE),
                        valor = ie.getString(Entrega.ITENS_VALOR)
                    )
                }.toTypedArray()
            )
        }
    }

    private fun getCollection(): MongoCollection<Document> {
        return mongoClient.getDatabase(databaseName).getCollection(collectionName, Document::class.java)
    }
}