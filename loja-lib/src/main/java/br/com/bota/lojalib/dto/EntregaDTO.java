package br.com.bota.lojalib.dto;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "itens")
public class EntregaDTO implements Serializable {
    private UUID pedidoId;
    private String cliente;
    private String endereco;
    private Set<ItemEntregaDTO> itens;
    private BigDecimal valorTotal;

    public String getPedidoIdAsString() {
        return pedidoId != null ? pedidoId.toString() : "";
    }

    public String getValorTotalAsString() {
        return valorTotal != null ? valorTotal.toString() : "0,00";
    }
}