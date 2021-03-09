package br.com.bota.entity;

import br.com.bota.entity.listener.CustomerListener;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

//lombok
@Data
@EqualsAndHashCode(exclude = "localizacao")
@ToString(exclude = "localizacao")
//jpa
@Entity
@Table(name = "clientes")
@EntityListeners(CustomerListener.class)
//micronaut
@Introspected
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cliente_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @NotBlank(message = "{cliente.nome.vazio}")
    @Max(value = 100, message = "{cliente.nome.tamanho.max}")
    @Min(value = 1, message = "{cliente.nome.tamanho.min}")
    @Column(name = "cliente_nome", length = 100, nullable = false)
    private String nome;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    @Valid
    private Localizacao localizacao;
    @Column(name = "cliente_cadastrado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    @Column(name = "cliente_atualizado_em", nullable = true, insertable = false)
    private LocalDateTime modificadoEm;
}
