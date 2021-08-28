package br.com.bota.resource.exception

import br.com.bota.service.rule.PedidoNotFoundException
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import jakarta.inject.Singleton

@Produces
@Singleton
@Requires(classes = [PedidoNotFoundException::class, ExceptionHandler::class])
class ExceptionResource : ExceptionHandler<PedidoNotFoundException, HttpResponse<*>> {
    override fun handle(request: HttpRequest<*>, exception: PedidoNotFoundException): HttpResponse<*> =
        HttpResponse.notFound(JsonError(exception.message))
}