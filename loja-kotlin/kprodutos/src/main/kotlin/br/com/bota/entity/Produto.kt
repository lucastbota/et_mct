package br.com.bota.entity

import br.com.bota.entity.listener.ProdutoListener
import io.micronaut.core.annotation.Introspected
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "produtos")
@EntityListeners(ProdutoListener::class)
@Introspected
open class Produto(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produto_id", columnDefinition = "BINARY(16)")
    open var id: UUID? = null,
    @field:NotBlank(message = "{produto.descricao.vazio}")
    @Size(min = 1, max = 100, message = "{produto.descricao.tamanho}")
    @Column(name = "produto_descricao", length = 100, nullable = false)
    open  var descricao: String?=null,
    @NotNull(message = "{produto.valor.vazio}")
    @Column(name = "produto_valor", nullable = false)
    open var valor: BigDecimal?=null,
    @NotNull(message = "{produto.quantidade.vazia}")
    @Min(value = 1, message = "{produto.quantidade.minimo}")
    open var quantidade: Int?=null,
    @Column(name = "produto_cadastrado_em", nullable = false, updatable = false)
    open var criadoEm: LocalDateTime?=null,
    @Column(name = "produto_atualizado_em", nullable = true, insertable = false)
    open var modificadoEm: LocalDateTime?=null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Produto

        if (id != other.id) return false
        if (descricao != other.descricao) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (descricao?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "id=$id, descricao=$descricao, valor=$valor, quantidade=$quantidade, criadoEm=$criadoEm, modificadoEm=$modificadoEm"
    }

}