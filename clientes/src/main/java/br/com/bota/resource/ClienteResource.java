package br.com.bota.resource;

import br.com.bota.dto.ClienteDTO;
import br.com.bota.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller("/cliente")
public class ClienteResource {
    @Inject
    private ClienteService service;

    @Get("/{id}")
    public HttpResponse<ClienteDTO> findById(UUID id) {
        return HttpResponse.ok(ClienteDTO.toDTO(service.findById(id)));
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<ClienteDTO>> findAll() {
        var customers = service.findAll();
        return HttpResponse.ok(customers.stream().map(ClienteDTO::toDTO).collect(Collectors.toList()));
    }

    @Post
    public HttpResponse<ClienteDTO> save(ClienteDTO dto) {
        var saved = service.save(ClienteDTO.toEntity(dto));
        return HttpResponse.created(ClienteDTO.toDTO(saved));
    }

    @Patch
    public HttpResponse<ClienteDTO> update(ClienteDTO dto) {
        var saved = service.save(ClienteDTO.toEntity(dto));
        return HttpResponse.created(ClienteDTO.toDTO(saved));
    }
}