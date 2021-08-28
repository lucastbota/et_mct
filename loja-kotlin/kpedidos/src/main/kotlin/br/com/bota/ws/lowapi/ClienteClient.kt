package br.com.bota.ws.lowapi

import br.com.bota.lojalib.dto.ClienteDTO
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import io.reactivex.Flowable
import io.reactivex.Maybe
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
open class ClienteClient @Inject constructor(@param:Client("\${api.url.cliente}")  val httpClient: HttpClient) {
    fun buscarClientePorId(clienteId: UUID) : Maybe<ClienteDTO> {
        val req:HttpRequest<ClienteDTO> = HttpRequest.GET(UriBuilder.of(clienteId.toString()).build())
        val flowable = Flowable.fromPublisher(httpClient.retrieve(req, Argument.of(ClienteDTO::class.java)))
        return flowable.firstElement()
    }
}