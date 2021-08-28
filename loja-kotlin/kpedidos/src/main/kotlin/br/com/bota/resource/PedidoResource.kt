package br.com.bota.resource

import br.com.bota.converter.PedidoConverter
import br.com.bota.facade.PedidoFacade
import br.com.bota.lojalib.dto.PedidoDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import jakarta.inject.Inject
import java.util.*


@Controller("/pedido")
open class PedidoResource @Inject constructor(private val facade: PedidoFacade) {


    @Post
    @ContinueSpan
    open fun criar(@SpanTag("pedido.criar") dto: PedidoDTO): HttpResponse<PedidoDTO> {
        var pedidoConvertido = facade.criar(PedidoConverter.toPedido(dto))
        return HttpResponse.created(PedidoConverter.toPedidoDTO(pedidoConvertido))
    }

    @Get("/{clienteId}")
    @ContinueSpan
    open fun findByClienteId(@SpanTag("cliente.get") @PathVariable("clienteId") id: UUID): HttpResponse<List<PedidoDTO>> {
        val resultados = facade.findById(id)
        return HttpResponse.ok(resultados.map { PedidoConverter.toPedidoDTO(it) })
    }
}