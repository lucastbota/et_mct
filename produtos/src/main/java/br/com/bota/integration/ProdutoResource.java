package br.com.bota.integration;

import br.com.bota.dto.ProdutoDTO;
import br.com.bota.service.ProdutoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller("/produto")
public class ProdutoResource {
    @Inject
    private ProdutoService productService;

    @Get("/{id}")
    public HttpResponse<ProdutoDTO> findById(UUID id) {
        return HttpResponse.ok(ProdutoDTO.toDTO(productService.procurarPorId(id)));
    }

    @Get("/check/{id}")
    public HttpResponse<Boolean> checkQuantity(UUID id) {
        return HttpResponse.ok(productService.verificarQuantidade(id));
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<List<ProdutoDTO>> findAll() {
        var products = productService.procurar();
        return HttpResponse.ok(products.stream().map(ProdutoDTO::toDTO).collect(Collectors.toList()));
    }

    @Post
    public HttpResponse<ProdutoDTO> save(ProdutoDTO dto) {
        var saved = productService.salvar(ProdutoDTO.toEntity(dto));
        return HttpResponse.created(ProdutoDTO.toDTO(saved));
    }

    @Patch
    public HttpResponse<ProdutoDTO> update(ProdutoDTO dto) {
        var saved = productService.salvar(ProdutoDTO.toEntity(dto));
        return HttpResponse.created(ProdutoDTO.toDTO(saved));
    }
}