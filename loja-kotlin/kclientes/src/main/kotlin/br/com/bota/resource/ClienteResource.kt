package br.com.bota.resource

import br.com.bota.converter.ClienteConverter
import br.com.bota.lojalib.dto.ClienteDTO
import br.com.bota.service.ClienteService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import java.util.*
import jakarta.inject.Inject

@Controller("/cliente")
open class ClienteResource @Inject constructor(private val clienteService: ClienteService) {

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ContinueSpan
    open fun findById(@SpanTag("cliente.id") id: UUID): HttpResponse<ClienteDTO> {
        return HttpResponse.ok(ClienteConverter.toDTO(clienteService.findById(id)))
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @ContinueSpan
    open fun findAll(): HttpResponse<List<ClienteDTO>> {
        val clientes = clienteService.findAll()
        val clientesDTO = clientes.map { ClienteConverter.toDTO(it) }
        return HttpResponse.ok(clientesDTO)
    }

    @Post
    @ContinueSpan
    @Produces(MediaType.APPLICATION_JSON)
    open fun save(@SpanTag("payload") dto: ClienteDTO): HttpResponse<ClienteDTO> {
        val saved = clienteService.save(ClienteConverter.toEntity(dto))
        return HttpResponse.created(ClienteConverter.toDTO(saved))
    }

    @Patch
    @ContinueSpan
    open fun update(@SpanTag("payload") dto: ClienteDTO): HttpResponse<ClienteDTO> {
        val saved = clienteService.save(ClienteConverter.toEntity(dto))
        return HttpResponse.created(ClienteConverter.toDTO(saved))
    }
}