package br.com.bota.repository

import br.com.bota.entity.Cliente
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ClienteRepository : JpaRepository<Cliente, UUID> {
}