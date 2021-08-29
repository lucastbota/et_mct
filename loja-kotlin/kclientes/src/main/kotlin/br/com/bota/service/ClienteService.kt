package br.com.bota.service

import br.com.bota.entity.Cliente
import java.util.*

interface ClienteService {
    fun save(cliente:  Cliente ) : Cliente
    fun findById(id: UUID) : Cliente
    fun findAll() : List<Cliente>
}
