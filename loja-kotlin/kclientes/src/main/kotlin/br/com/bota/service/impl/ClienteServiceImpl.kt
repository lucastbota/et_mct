package br.com.bota.service.impl

import br.com.bota.entity.Cliente
import br.com.bota.exceptions.rule.CustomerNotFoundException
import br.com.bota.repository.ClienteRepository
import br.com.bota.service.ClienteService
import java.util.*
import jakarta.inject.Inject
import jakarta.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Singleton
open class ClienteServiceImpl(
    @Inject
    private val repository: ClienteRepository
) : ClienteService {

    override fun findAll(): List<Cliente> {
        return repository.findAll()
    }

    @Transactional
    override fun save(@Valid cliente: Cliente): Cliente {
        return if (cliente.id === null) {
            repository.save(cliente)
        } else {
            val existent = findById(cliente.id!!)
            existent.nome = cliente.nome
            existent
        }
    }

    override fun findById(id: UUID): Cliente {
        return repository.findById(id).orElseThrow { CustomerNotFoundException() }
    }
}