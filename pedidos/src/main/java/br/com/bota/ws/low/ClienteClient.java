package br.com.bota.ws.low;

import br.com.bota.dto.ClienteDTO;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class ClienteClient {
    @Inject
    @Client("${api.url.cliente}")
    private RxHttpClient httpClient;

   public Maybe<ClienteDTO> buscarClientePorId(UUID clienteId) {
        HttpRequest<?> req = HttpRequest.GET(UriBuilder.of(clienteId.toString()).build());
        Flowable flowable = httpClient.retrieve(req, Argument.of(ClienteDTO.class));
        return flowable.firstElement();
    }
}
