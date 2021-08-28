package br.com.bota.service

import br.com.bota.entity.Produto
import java.util.*

interface ProdutoService {
    fun salvar( produto: Produto) : Produto
    fun baixarEstoque(id: UUID, quantidade:Int)
    fun procurarPorId(id: UUID ): Produto
    fun verificarQuantidade(id: UUID ): Boolean
    fun procurar() : List<Produto>
}