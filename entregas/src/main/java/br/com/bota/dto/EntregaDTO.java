package br.com.bota.dto;


import br.com.bota.entity.Entrega;
import br.com.bota.entity.ItemEntrega;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "itens")
public class EntregaDTO implements Serializable {
    private String pedidoId;
    private String cliente;
    private String endereco;
    private Set<ItemEntregaDTO> itens;
    private String valorTotal;

    public static Entrega toDocument(EntregaDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setPedidoId(dto.getPedidoId());
        entrega.setCliente(dto.cliente);
        entrega.setEndereco(dto.getEndereco());
        entrega.setValorTotal(dto.getValorTotal());
        if (dto.getItens() != null)
            entrega.setItens(dto.getItens().stream().map(i -> ItemEntrega.builder().produto(i.getProduto()).quantidade(i.getQuantidade()).valor(Objects.toString(i.getValor())).build()).toArray(ItemEntrega[]::new));
        return entrega;
    }
}
