package br.com.bota.resource.exception;

import br.com.bota.service.rule.ProdutoNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {ProdutoNotFoundException.class, ExceptionHandler.class})
public class ExceptionResource implements ExceptionHandler<ProdutoNotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, ProdutoNotFoundException exception) {
        JsonError jsonError = new JsonError(exception.getMessage())
                .link(Link.SELF, Link.of(request.getUri()));
        return HttpResponse.notFound().body(jsonError);
    }
}