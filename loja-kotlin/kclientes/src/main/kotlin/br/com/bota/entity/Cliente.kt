package br.com.bota.entity

import br.com.bota.entity.listener.ClienteListener
import io.micronaut.core.annotation.Introspected
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@Introspected
@Entity
@Table(name = "clientes")
@EntityListeners(ClienteListener::class)
open class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cliente_id", columnDefinition = "BINARY(16)")
    open var id: UUID? = null,
    @field:NotBlank(message = "{cliente.nome.vazio}")
    @Max(value = 100, message = "{cliente.nome.tamanho.max}")
    @Min(value = 1, message = "{cliente.nome.tamanho.min}")
    @Column(name = "cliente_nome", length = 100, nullable = false)
    open var nome: String?=null,
    @OneToOne(cascade = [CascadeType.ALL], mappedBy = "cliente")
    @Valid
    open var localizacao: Localizacao? = null,
    @Column(name = "cliente_cadastrado_em", nullable = false, updatable = false)
    open var criadoEm: LocalDateTime? = null,
    @Column(name = "cliente_atualizado_em", nullable = true, insertable = false)
    open var modificadoEm: LocalDateTime? = null
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cliente

        if (id != other.id) return false
        if (nome != other.nome) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (nome?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "Cliente: $id | $nome | $criadoEm | $modificadoEm"
}