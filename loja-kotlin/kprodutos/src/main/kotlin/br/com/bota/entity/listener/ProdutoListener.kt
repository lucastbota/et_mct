package br.com.bota.entity.listener

import br.com.bota.entity.Produto
import java.time.LocalDateTime
import javax.persistence.PrePersist
import javax.persistence.PreUpdate


open class ProdutoListener {
    @PrePersist
    open fun onSave(produto: Produto) {
        produto.criadoEm = LocalDateTime.now()
    }

    @PreUpdate
    open fun onUpdate(produto: Produto) {
        produto.modificadoEm = LocalDateTime.now()
    }
}