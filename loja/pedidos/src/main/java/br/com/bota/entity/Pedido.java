package br.com.bota.entity;

import br.com.bota.entity.enums.PedidoStatusEnum;
import br.com.bota.entity.listener.PedidoListener;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(exclude = "itens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
@EntityListeners(PedidoListener.class)
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedido_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "pedido_realizado_em", nullable = false)
    private LocalDateTime pedidoRealizadoEm;
    @Column(name = "pedido_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PedidoStatusEnum status;
    @Transient
    private BigDecimal valorTotal;
    @Column(name = "cliente_id", columnDefinition = "BINARY(16)")
    private UUID clienteId;
    @Valid
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    public BigDecimal getValorTotal() {
        return this.getItens().stream().filter(Objects::nonNull).map(i -> i.getValor().multiply(BigDecimal.valueOf(i.getQuantidade()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
