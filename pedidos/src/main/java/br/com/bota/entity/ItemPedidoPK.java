package br.com.bota.entity;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoPK implements Serializable {
    private Pedido pedido;
    private UUID produtoId;
}
