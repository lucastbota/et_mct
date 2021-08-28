package br.com.bota.service

import br.com.bota.entity.Cliente
import java.util.*
import javax.validation.Valid

interface ClienteService {
    fun save(@Valid cliente:  Cliente ) : Cliente
    fun findById(id: UUID) : Cliente
    fun findAll() : List<Cliente>
}
