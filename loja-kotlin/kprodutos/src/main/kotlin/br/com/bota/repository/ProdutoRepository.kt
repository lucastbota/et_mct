package br.com.bota.repository

import br.com.bota.entity.Produto
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ProdutoRepository : JpaRepository<Produto, UUID>


