package br.com.bota.dto;

import br.com.bota.entity.Pedido;
import br.com.bota.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoDTO implements Serializable {
    private UUID pedidoId;
    private UUID produtoId;
    private Integer quantidade;
    private BigDecimal valor;

    public static ItemPedidoDTO toDTO(ItemPedido entity) {
        var dto = new ItemPedidoDTO();
        dto.setPedidoId(entity.getPedido().getId());
        dto.setProdutoId(entity.getProdutoId());
        dto.setQuantidade(entity.getQuantidade());
        dto.setValor(entity.getValor());
        return dto;
    }

    public static ItemPedido toEntity(ItemPedidoDTO dto) {
        var entity = new ItemPedido();
        entity.setPedido(Pedido.builder().id(dto.getPedidoId()).build());
        entity.setProdutoId(dto.getProdutoId());
        entity.setValor(dto.getValor());
        entity.setQuantidade(dto.getQuantidade());
        return entity;
    }
}
