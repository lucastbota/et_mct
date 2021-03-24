package br.com.bota.integration;

import br.com.bota.dto.ProdutoDTO;
import br.com.bota.service.ProdutoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.NewSpan;
import io.micronaut.tracing.annotation.SpanTag;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller("/produto")
public class ProdutoResource {
    @Inject
    private ProdutoService productService;

    @Get("/{id}")
    @ContinueSpan
    public HttpResponse<ProdutoDTO> findById(@SpanTag("produto.find.id") UUID id) {
        return HttpResponse.ok(ProdutoDTO.toDTO(productService.procurarPorId(id)));
    }

    @Get("/check/{id}")
    @ContinueSpan
    public HttpResponse<Boolean> checkQuantity(UUID id) {
        return HttpResponse.ok(productService.verificarQuantidade(id));
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    @ContinueSpan
    public HttpResponse<List<ProdutoDTO>> findAll() {
        var products = productService.procurar();
        return HttpResponse.ok(products.stream().map(ProdutoDTO::toDTO).collect(Collectors.toList()));
    }

    @Post
    @ContinueSpan
    public HttpResponse<ProdutoDTO> save(@SpanTag("produto.payload") ProdutoDTO dto) {
        var saved = productService.salvar(ProdutoDTO.toEntity(dto));
        return HttpResponse.created(ProdutoDTO.toDTO(saved));
    }

    @Patch
    @ContinueSpan
    public HttpResponse<ProdutoDTO> update(ProdutoDTO dto) {
        var saved = productService.salvar(ProdutoDTO.toEntity(dto));
        return HttpResponse.created(ProdutoDTO.toDTO(saved));
    }
}