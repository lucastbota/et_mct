package br.com.bota.resource.exception

import br.com.bota.exceptions.rule.CustomerNotFoundException
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Produces
@Singleton
@Requires(classes = [CustomerNotFoundException::class, ExceptionHandler::class])
open class CustomerNotFoundExceptioHandler : ExceptionHandler<CustomerNotFoundException, HttpResponse<Any>>{
    override fun handle(request: HttpRequest<Any>, exception: CustomerNotFoundException?): HttpResponse<Any> {
       return HttpResponse.notFound(JsonError(exception?.message))
    }
}