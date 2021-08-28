package br.com.bota.repository

import br.com.bota.entity.Pedido
import br.com.bota.lojalib.enums.PedidoStatusEnum
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface PedidoRepository : JpaRepository<Pedido, UUID> {
    fun findByClienteIdOrderById(id: UUID): List<Pedido>
    fun findByStatusIn(status: Set<PedidoStatusEnum>): List<Pedido>
}