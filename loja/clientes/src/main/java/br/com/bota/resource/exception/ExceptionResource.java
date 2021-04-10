package br.com.bota.resource.exception;

import br.com.bota.service.rule.CustomerNotFoundException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;

@Produces
@Singleton
@Requires(classes = {CustomerNotFoundException.class, ExceptionHandler.class})
public class ExceptionResource implements ExceptionHandler<CustomerNotFoundException, HttpResponse> {
    @Override
    public HttpResponse handle(HttpRequest request, CustomerNotFoundException exception) {
        JsonError error = new JsonError(exception.getMessage());
        return HttpResponse.notFound(error);
    }
}
