package br.com.bota.lojalib.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoDTO extends ItemDTO {
    private UUID pedidoId;
    private UUID produtoId;
    private Integer quantidade;
    private BigDecimal valor;
}
