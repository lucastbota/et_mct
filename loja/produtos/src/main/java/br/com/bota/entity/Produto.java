package br.com.bota.entity;

import br.com.bota.entity.listener.ProdutoListener;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

//lombok
@Data
//jpa
@Entity
@Table(name = "produtos")
@EntityListeners(ProdutoListener.class)
//micronaut
@Introspected
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @NotBlank(message = "{produto.descricao.vazio}")
    @Size(min = 1, max=100, message = "{produto.descricao.tamanho}")
    @Column(name = "produto_descricao", length = 100, nullable = false)
    private String descricao;
    @NotNull(message = "{produto.valor.vazio}")
    @Column(name="produto_valor", nullable = false)
    private BigDecimal valor;
    @NotNull(message="{produto.quantidade.vazia}")
    @Min(value = 1, message = "{produto.quantidade.minimo}")
    private Integer quantidade;
    @Column(name = "produto_cadastrado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;
    @Column(name = "produto_atualizado_em", nullable = true, insertable = false)
    private LocalDateTime modificadoEm;
}
