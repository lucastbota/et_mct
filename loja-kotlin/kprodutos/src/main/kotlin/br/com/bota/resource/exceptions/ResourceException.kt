package br.com.bota.resource.exceptions

import br.com.bota.exceptions.ProdutoNotFoundException
import io.micronaut.context.annotation.Replaces
import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.server.exceptions.response.ErrorContext
import io.micronaut.http.server.exceptions.response.ErrorResponseProcessor
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException

@Produces
@Singleton
@Requires(classes = [ProdutoNotFoundException::class, ExceptionHandler::class])
open class ExceptionResource : ExceptionHandler<ProdutoNotFoundException, HttpResponse<JsonError>> {
    override fun handle(request: HttpRequest<*>?, exception: ProdutoNotFoundException?): HttpResponse<JsonError> =
        HttpResponse.notFound(JsonError(exception?.message).link(Link.SELF, Link.of(request?.uri)))
}

@Produces
@Singleton
@Replaces(value = io.micronaut.validation.exceptions.ConstraintExceptionHandler::class)
@Requirements(
    Requires(classes = [ConstraintViolationException::class, ExceptionHandler::class])
)
open class ConstraintValidationResource(private val errorResponseProcessor: ErrorResponseProcessor<Any>) :
    ExceptionHandler<ConstraintViolationException, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>, exception: ConstraintViolationException): HttpResponse<Any> {
        val messages = exception.constraintViolations.joinToString("\n") {
            it.message
        }
        return errorResponseProcessor.processResponse(
            ErrorContext.builder(request)
                .cause(exception)
                .errorMessage(messages)
                .build(), HttpResponse.badRequest(JsonError(messages).link(Link.SELF, Link.of(request.uri)))
        )
    }
}