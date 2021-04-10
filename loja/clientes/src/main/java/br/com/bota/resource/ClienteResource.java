package br.com.bota.resource;

import br.com.bota.dto.ClienteDTO;
import br.com.bota.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.SpanTag;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller("/cliente")
public class ClienteResource {
    @Inject
    private ClienteService service;

    @Get("/{id}")
    @ContinueSpan()
    public HttpResponse<ClienteDTO> findById(@SpanTag("cliente.id") UUID id) {
        return HttpResponse.ok(ClienteDTO.toDTO(service.findById(id)));
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @ContinueSpan
    public HttpResponse<List<ClienteDTO>> findAll() {
        var customers = service.findAll();
        return HttpResponse.ok(customers.stream().map(ClienteDTO::toDTO).collect(Collectors.toList()));
    }

    @Post
    @ContinueSpan
    public HttpResponse<ClienteDTO> save(@SpanTag("payload")ClienteDTO dto) {
        var saved = service.save(ClienteDTO.toEntity(dto));
        return HttpResponse.created(ClienteDTO.toDTO(saved));
    }

    @Patch
    @ContinueSpan
    public HttpResponse<ClienteDTO> update(@SpanTag("payload") ClienteDTO dto) {
        var saved = service.save(ClienteDTO.toEntity(dto));
        return HttpResponse.created(ClienteDTO.toDTO(saved));
    }
}