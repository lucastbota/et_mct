package br.com.bota.dto;

import br.com.bota.entity.Pedido;
import br.com.bota.entity.ItemPedido;
import br.com.bota.entity.enums.PedidoStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PedidoDTO implements Serializable {
    private UUID id;
    private LocalDateTime pedidoRealizadoEm;
    private PedidoStatusEnum status;
    private UUID clienteId;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itens;

    public static PedidoDTO toDTO(Pedido entity) {
        var dto = new PedidoDTO();
        dto.setId(entity.getId());
        dto.setClienteId(entity.getClienteId());
        dto.setPedidoRealizadoEm(entity.getPedidoRealizadoEm());
        dto.setStatus(entity.getStatus());
        dto.setItens(entity.getItens().stream().map(toPedidoItemDTO()).collect(Collectors.toList()));
        dto.setValorTotal(entity.getValorTotal());
        return dto;
    }

    public static Pedido toEntity(PedidoDTO dto) {
        var entity = new Pedido();
        entity.setId(dto.getId());
        entity.setClienteId(dto.getClienteId());
        entity.setPedidoRealizadoEm(dto.getPedidoRealizadoEm());
        entity.setStatus(dto.getStatus());
        entity.setItens(dto.getItens().stream().map(toPedidoItem()).collect(Collectors.toList()));
        return entity;
    }

    private static Function<ItemPedidoDTO, ItemPedido> toPedidoItem() {
        return pi ->
                ItemPedido.builder().produtoId(pi.getProdutoId()).valor(pi.getValor()).quantidade(pi.getQuantidade()).build();
    }

    private static Function<ItemPedido, ItemPedidoDTO> toPedidoItemDTO() {
        return pi ->
                ItemPedidoDTO.builder().pedidoId(pi.getPedido().getId()).produtoId(pi.getProdutoId()).valor(pi.getValor()).quantidade(pi.getQuantidade()).build();
    }
}
