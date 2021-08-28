package br.com.bota.lojalib.dto;

import br.com.bota.lojalib.enums.PedidoStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO implements Serializable {
    private UUID id;
    private LocalDateTime pedidoRealizadoEm;
    private PedidoStatusEnum status;
    private UUID clienteId;
    private BigDecimal valorTotal;
    private List<ItemPedidoDTO> itens;
}
