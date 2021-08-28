package br.com.bota.ws.highapi

import br.com.bota.lojalib.dto.ProdutoDTO
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Flowable
import java.util.*

@Client("\${api.url.produto}")
interface ProdutoClient {
    @Get("/{id}")
    fun buscarPorId(  id: UUID) : Flowable<ProdutoDTO>
}