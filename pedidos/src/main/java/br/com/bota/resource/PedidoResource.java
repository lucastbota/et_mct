package br.com.bota.resource;

import br.com.bota.dto.PedidoDTO;
import br.com.bota.facade.PedidoFacade;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller("/pedido")
public class PedidoResource {
    @Inject
    private PedidoFacade facade;

    @Post
    public HttpResponse<PedidoDTO> criar(PedidoDTO dto) {
        var toSave = facade.criar(PedidoDTO.toEntity(dto));
        return HttpResponse.ok(PedidoDTO.toDTO(toSave));
    }

    @Get("/{clienteId}")
    public HttpResponse<List<PedidoDTO>> findByClienteId(@PathVariable("clienteId") UUID id) {
        var resultados = facade.findBy(id);
        return HttpResponse.ok(resultados.stream().map(PedidoDTO::toDTO).collect(Collectors.toList()));
    }
}
