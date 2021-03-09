package br.com.bota.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedido_itens")
@IdClass(ItemPedidoPK.class)
public class ItemPedido implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @Id
    @Column(name = "produto_id")
    private UUID produtoId;
    @Column(name = "quantidade_item")
    private Integer quantidade;
    @Column(name = "valor")
    private BigDecimal valor;
}
