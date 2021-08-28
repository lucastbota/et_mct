package br.com.bota.resource

import br.com.bota.converter.ProdutoConverter
import br.com.bota.lojalib.dto.ProdutoDTO
import br.com.bota.service.ProdutoService
import br.com.bota.service.impl.ProdutoServiceImpl
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.tracing.annotation.ContinueSpan
import jakarta.inject.Inject
import java.util.*

interface ProdutoResource {
    fun findById(id: UUID): HttpResponse<ProdutoDTO>
    fun verificarQuantidade(id: UUID): HttpResponse<Boolean>
    fun findAll(): HttpResponse<List<ProdutoDTO>>
    fun save(dto: ProdutoDTO): HttpResponse<ProdutoDTO>
    fun update(dto: ProdutoDTO): HttpResponse<ProdutoDTO>
}

@Controller("/produto")
open class ProdutoResourceImpl @Inject constructor(private val produtoService: ProdutoService): ProdutoResource{
    @Get("/{id}")
    @ContinueSpan
    override fun findById(id: UUID): HttpResponse<ProdutoDTO> =
        HttpResponse.ok(ProdutoConverter.toProdutoDTO(produtoService.procurarPorId(id)))

    @Get("/check/{id}")
    @ContinueSpan
    override fun verificarQuantidade(id: UUID): HttpResponse<Boolean> =
        HttpResponse.ok(produtoService.verificarQuantidade(id))

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @ContinueSpan
    override fun findAll(): HttpResponse<List<ProdutoDTO>> {
        val produtos = produtoService.procurar()
        return HttpResponse.ok(produtos.map { ProdutoConverter.toProdutoDTO(it) })
    }

    @Post
    @ContinueSpan
    override fun save(dto: ProdutoDTO): HttpResponse<ProdutoDTO> =
        HttpResponse.created(ProdutoConverter.toProdutoDTO(produtoService.salvar(ProdutoConverter.toProduto(dto))))

    @Patch
    @ContinueSpan
    override fun update(dto: ProdutoDTO): HttpResponse<ProdutoDTO> {
        return HttpResponse.created(ProdutoConverter.toProdutoDTO(produtoService.salvar(ProdutoConverter.toProduto(dto))))
    }
}