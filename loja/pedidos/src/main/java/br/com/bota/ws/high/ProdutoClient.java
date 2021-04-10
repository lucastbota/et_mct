package br.com.bota.ws.high;

import br.com.bota.dto.ProdutoDTO;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Flowable;

import java.util.UUID;

@Client("${api.url.produto}")
public interface ProdutoClient {
    @Get("/{id}")
    Flowable<ProdutoDTO> buscarPorId(UUID id);
}
