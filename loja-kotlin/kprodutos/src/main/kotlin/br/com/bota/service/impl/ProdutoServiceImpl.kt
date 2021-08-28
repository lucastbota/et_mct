package br.com.bota.service.impl

import br.com.bota.entity.Produto
import br.com.bota.exceptions.ProdutoNotFoundException
import br.com.bota.repository.ProdutoRepository
import br.com.bota.service.ProdutoService
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.transaction.Transactional

@Singleton
open class ProdutoServiceImpl @Inject constructor(private val repository: ProdutoRepository) : ProdutoService {
    private val log: Logger = LoggerFactory.getLogger(ProdutoServiceImpl::class.java)

    @Transactional
    override fun salvar(produto: Produto): Produto {
        return if (produto.id == null) {
            repository.save(produto)
        } else {
            val existente = this.procurarPorId(produto.id!!)
            existente.descricao = produto.descricao
            existente.quantidade = produto.quantidade
            existente.valor = produto.valor
            repository.update(existente)
        }
    }

    @Transactional
    override fun baixarEstoque(id: UUID, quantidade: Int) {
        val produto = procurarPorId(id)
        log.info("Antes de baixar o estoque: {}", produto)
        produto.quantidade = produto.quantidade?.minus(quantidade)
        log.info("Depois de baixar o estoque: {}", produto)
        repository.update(produto)
    }

    override fun procurarPorId(id: UUID): Produto = repository.findById(id).orElseThrow { ProdutoNotFoundException() }

    override fun verificarQuantidade(id: UUID): Boolean = this.procurarPorId(id).quantidade!! > 0

    override fun procurar(): List<Produto> = repository.findAll()
}