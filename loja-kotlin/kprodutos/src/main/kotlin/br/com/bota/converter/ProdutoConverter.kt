package br.com.bota.converter

import br.com.bota.entity.Produto
import br.com.bota.lojalib.dto.ProdutoDTO

class ProdutoConverter {
    companion object {
        fun toProdutoDTO(entidade: Produto): ProdutoDTO =
            ProdutoDTO(
                entidade.id,
                entidade.descricao,
                entidade.valor,
                entidade.quantidade,
                entidade.criadoEm,
                entidade.modificadoEm
            )

        fun toProduto(dto: ProdutoDTO): Produto =
            Produto(dto.id, dto.descricao, dto.valor, dto.quantidade, dto.criadoEm, dto.modificadoEm)
    }
}