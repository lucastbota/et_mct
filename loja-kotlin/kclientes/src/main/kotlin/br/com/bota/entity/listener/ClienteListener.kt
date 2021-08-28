package br.com.bota.entity.listener

import br.com.bota.entity.Cliente
import java.time.LocalDateTime
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class ClienteListener {

    @PrePersist
    fun onSave(cliente: Cliente) {
        cliente.criadoEm = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate(cliente: Cliente) {
        cliente.modificadoEm = LocalDateTime.now()
    }

}