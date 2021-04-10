package br.com.bota.resource.exception;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Collectors;

@Produces
@Singleton
@Replaces(io.micronaut.validation.exceptions.ConstraintExceptionHandler.class)
@Requires(classes = {ConstraintViolationException.class, ExceptionHandler.class})
public class ConstraintValidationResource implements ExceptionHandler<ConstraintViolationException, HttpResponse>{
    @Override
    public HttpResponse handle(HttpRequest request, ConstraintViolationException exception) {
       var errorMessage = exception.getConstraintViolations().stream().filter(Objects::nonNull).map(c -> c.getMessage()).collect(Collectors.joining("\n"));

        return HttpResponse.badRequest(new JsonError((errorMessage)));
    }
}
