package br.com.bota.repository.impl;

import br.com.bota.entity.Entrega;
import br.com.bota.entity.ItemEntrega;
import br.com.bota.repository.Repository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import io.micronaut.context.annotation.Value;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MongoRepository implements Repository {
    @Value("${mongodb.collectionName}")
    private String collectionName;
    @Value("${mongodb.databaseName}")
    private String databaseName;
    @Inject
    private MongoClient mongoClient;

    @Override
    public void salvar(Entrega entrega) {
        var document = new Entrega.EntregaDocumentBuilder().addPedidoId(entrega.getPedidoId())
                .addCliente(entrega.getCliente())
                .addEndereco(entrega.getEndereco())
                .addEntregou(entrega.getEntregou())
                .addPacoteRecebidoEm(entrega.getPacoteRecebidoEm())
                .addEntregaRealizadaEm(entrega.getEntregaRealizadaEm())
                .addAssinaturaCliente(entrega.getAssinaturaCliente())
                .addDocumentoCliente(entrega.getDocumentoCliente())
                .addItens(entrega.getItens())
                .addValorTotal(entrega.getValorTotal());
        getCollection().insertOne(document.build());
    }

    @Override
    public List<Entrega> getPacotesNaoEntregues() {
        Bson bsonFilter = Filters.eq("entregou", false);
        FindIterable<Document> result = getCollection()
                .find(bsonFilter);
        var resultAsArray = result.into(new ArrayList<>());
        return resultAsArray.stream().map(d -> {
            var entrega = new Entrega();
            entrega.setId(d.getObjectId(Entrega.ID));
            entrega.setPedidoId(d.getString(Entrega.PEDIDO_ID));
            entrega.setEntregou(d.getBoolean(Entrega.ENTREGOU));
            entrega.setEndereco(d.getString(Entrega.ENDERECO));
            entrega.setEntregaRealizadaEm(d.getString(Entrega.ENTREGA_REALIZADA_EM));
            entrega.setPacoteRecebidoEm(d.getString(Entrega.PACOTE_RECEBIDO_EM));
            entrega.setAssinaturaCliente(d.getString(Entrega.ASSINATURA_CLIENTE));
            entrega.setDocumentoCliente(d.getString(Entrega.DOCUMENTO_CLIENTE));
            entrega.setValorTotal(d.getString(Entrega.VALOR_TOTAL));
            entrega.setItens(d.getList(Entrega.ITENS, Document.class).stream().map(i -> {
                var item = new ItemEntrega();
                item.setProduto(i.getString(Entrega.ITENS_PRODUTO));
                item.setQuantidade(i.getInteger(Entrega.ITENS_QUANTIDADE));
                item.setValor(i.getString(Entrega.ITENS_VALOR));
                return item;
            }).toArray(ItemEntrega[]::new));
            return entrega;
        }).collect(Collectors.toList());
    }

    @Override
    public Entrega entregar(Entrega entrega) {
        var count = getCollection().updateOne(Filters.eq(Entrega.ID, entrega.getId()), Updates.combine(
                Updates.set(Entrega.ENTREGOU, entrega.getEntregou()),
                Updates.set(Entrega.ENTREGA_REALIZADA_EM, entrega.getEntregaRealizadaEm()),
                Updates.set(Entrega.ASSINATURA_CLIENTE, entrega.getAssinaturaCliente()),
                Updates.set(Entrega.DOCUMENTO_CLIENTE, entrega.getDocumentoCliente())
        )).getModifiedCount();
        return count > 0 ? entrega : null;
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase(databaseName).getCollection(collectionName,  Document.class);
    }
}