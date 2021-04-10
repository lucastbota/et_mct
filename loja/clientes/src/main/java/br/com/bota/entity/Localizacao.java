package br.com.bota.entity;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "localizacoes")
@Introspected
public class Localizacao implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;
    @NotNull(message = "{localizacao.latitude}")
    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;
    @NotNull(message = "{localizacao.longitude}")
    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;
}
