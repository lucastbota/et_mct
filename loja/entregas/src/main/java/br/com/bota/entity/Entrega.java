package br.com.bota.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega implements Serializable {
    public static final String ID = "_id";
    public static final String PEDIDO_ID = "pedido_id";
    public static final String CLIENTE = "cliente";
    public static final String ENDERECO = "endereco";
    public static final String ENTREGOU = "entregou";
    public static final String ENTREGA_REALIZADA_EM = "entrega_realizada_em";
    public static final String PACOTE_RECEBIDO_EM = "pacote_recebido_em";
    public static final String ASSINATURA_CLIENTE = "assinatura_cliente";
    public static final String DOCUMENTO_CLIENTE = "documento_cliente";
    public static final String ITENS = "itens";
    public static final String ITENS_PRODUTO = "produto";
    public static final String ITENS_QUANTIDADE = "quantidade";
    public static final String ITENS_VALOR = "valor";
    public static final String VALOR_TOTAL = "valor_total";

    private ObjectId id;
    private String pedidoId;
    private String cliente;
    private String endereco;
    private Boolean entregou;
    private String pacoteRecebidoEm;
    private String entregaRealizadaEm;
    private String assinaturaCliente;
    private String documentoCliente;
    private ItemEntrega[] itens;
    private String valorTotal;

    public static class EntregaDocumentBuilder {
        private ObjectId id;
        private String pedidoId;
        private String cliente;
        private String endereco;
        private Boolean entregou;
        private String pacoteRecebidoEm;
        private String entregaRealizadaEm;
        private String assinaturaCliente;
        private String documentoCliente;
        private ItemEntrega[] itens;
        private String valorTotal;


        public EntregaDocumentBuilder addId(ObjectId id) {
            this.id = id;
            return this;
        }

        public EntregaDocumentBuilder addPedidoId(String pedidoId) {
            this.pedidoId = pedidoId;
            return this;
        }

        public EntregaDocumentBuilder addCliente(String cliente) {
            this.cliente = cliente;
            return this;
        }

        public EntregaDocumentBuilder addEndereco(String endereco) {
            this.endereco = endereco;
            return this;
        }

        public EntregaDocumentBuilder addEntregou(Boolean entregou) {
            this.entregou = entregou;
            return this;
        }

        public EntregaDocumentBuilder addPacoteRecebidoEm(String pacoteRecebidoEm) {
            this.pacoteRecebidoEm = pacoteRecebidoEm;
            return this;
        }

        public EntregaDocumentBuilder addEntregaRealizadaEm(String entregaRealizadaEm) {
            this.entregaRealizadaEm = entregaRealizadaEm;
            return this;
        }

        public EntregaDocumentBuilder addAssinaturaCliente(String assinaturaCliente) {
            this.assinaturaCliente = assinaturaCliente;
            return this;
        }

        public EntregaDocumentBuilder addDocumentoCliente(String documentoCliente) {
            this.documentoCliente = documentoCliente;
            return this;
        }

        public EntregaDocumentBuilder addItens(ItemEntrega[] itens) {
            this.itens = itens;
            return this;
        }

        public EntregaDocumentBuilder addValorTotal(String valorTotal) {
            this.valorTotal = valorTotal;
            return this;
        }


        public Document build() {
            var document = new Document();
            if (id != null)
                document.put(ID, id);

            if (pedidoId != null)
                document.put(PEDIDO_ID, pedidoId);

            if (cliente != null)
                document.put(CLIENTE, cliente);

            if (endereco != null)
                document.put(ENDERECO, endereco);

            if (entregou != null)
                document.put(ENTREGOU, entregou);
            if (entregaRealizadaEm != null)
                document.put(ENTREGA_REALIZADA_EM, entregaRealizadaEm);
            if (pacoteRecebidoEm != null)
                document.put(PACOTE_RECEBIDO_EM, pacoteRecebidoEm);
            if (assinaturaCliente != null)
                document.put(ASSINATURA_CLIENTE, assinaturaCliente);
            if (documentoCliente != null)
                document.put(DOCUMENTO_CLIENTE, documentoCliente);

            if (itens != null) {
                document.put(ITENS, Stream.of(itens).map(i -> {
                    var itemDoc = new Document();
                    itemDoc.put(ITENS_PRODUTO, i.getProduto());
                    itemDoc.put(ITENS_QUANTIDADE, i.getQuantidade());
                    itemDoc.put(ITENS_VALOR, i.getValor());

                    return itemDoc;
                }).collect(Collectors.toList()));
            }
            if (valorTotal != null)
                document.put(VALOR_TOTAL, valorTotal);
            return document;
        }
    }
}
