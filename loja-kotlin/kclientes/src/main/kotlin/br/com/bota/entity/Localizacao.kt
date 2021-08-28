package br.com.bota.entity

import io.micronaut.core.annotation.Introspected
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "localizacoes")
@Introspected
open class Localizacao(
    @Id
    @OneToOne
    @JoinColumn(name = "cliente_id")
    open var cliente: Cliente? = null,
    @field:NotNull(message = "{localizacao.latitude}")
    @Column(name = "latitude", precision = 10, scale = 8)
    open var latitude: BigDecimal?,
    @field:NotNull(message = "{localizacao.longitude}")
    @Column(name = "longitude", precision = 11, scale = 8)
    open var longitude: BigDecimal?
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Localizacao

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }

    override fun toString(): String {
        return "Localizacao(latitude=$latitude, longitude=$longitude)"
    }

}
