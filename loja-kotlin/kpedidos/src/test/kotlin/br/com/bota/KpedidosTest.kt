package br.com.bota

import br.com.bota.lojalib.dto.ItemPedidoDTO
import br.com.bota.lojalib.dto.PedidoDTO
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.StreamingHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.*

@MicronautTest
class KpedidosTest {
    @Inject
    @field:Client("/pedido")
    lateinit var streamingHttpClient: StreamingHttpClient

    @Test
    fun `test create process`() {
        val smartphone = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(1499.99))
            .produtoId(UUID.fromString("53f2dbc0-179c-4b91-a231-8a8025de5bd2")).quantidade(3).build()
        val laptop = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(12600.0))
            .produtoId(UUID.fromString("f5ae9761-7c8f-4ac3-aa8f-2b5b03961e93")).quantidade(2).build()
        val tablet = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(2500.0))
            .produtoId(UUID.fromString("fc871373-616e-437d-af2c-bebca1dedbb2")).quantidade(7).build()

        val pedido = PedidoDTO()
        pedido.itens = mutableListOf(smartphone, laptop, tablet)
        pedido.clienteId = UUID.fromString("e2676282-8126-4a23-863e-8a3bbcb4fab3")

        val request: HttpRequest<PedidoDTO> = HttpRequest.POST("/", pedido)
        val body = streamingHttpClient.toBlocking().retrieve(request, PedidoDTO::class.java)
        Assertions.assertNotNull(body)
        Assertions.assertTrue(BigDecimal.valueOf(47199.97).compareTo(body.valorTotal) == 0)
    }
}