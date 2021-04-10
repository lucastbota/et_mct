package br.com.bota.resource.exception;

import br.com.bota.service.rule.PedidoNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {PedidoNotFoundException.class, ExceptionHandler.class})
public class ExceptionResource implements ExceptionHandler<PedidoNotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, PedidoNotFoundException exception) {
        JsonError jsonError = new JsonError(exception.getMessage());
        return HttpResponse.notFound(jsonError);
    }
}
