package br.com.bota

import br.com.bota.entity.Cliente
import br.com.bota.entity.Localizacao
import br.com.bota.lojalib.dto.ClienteDTO
import br.com.bota.lojalib.dto.LocalizacaoDTO
import br.com.bota.repository.ClienteRepository
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.StreamingHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@MicronautTest
class ClienteResourceIT {

    @Inject
    @field:Client("/cliente")
    lateinit var streamingHttpClient: StreamingHttpClient
    @Inject
    lateinit var clienteRepository: ClienteRepository
    var cliente: Cliente? = null

    @BeforeEach
    fun setUp() {
        val local = Localizacao(latitude=  BigDecimal.valueOf(-49.3815748), longitude = BigDecimal.valueOf(-20.8168906))
       val cliente = Cliente(nome= "Teresa do sorriso aparente")
        cliente.localizacao = local
        local.cliente = cliente
        this.cliente = clienteRepository.save(cliente)
    }

    @AfterEach
    fun clean() {
        val clientes = clienteRepository.findAll()
        clienteRepository.deleteAll(clientes)
    }

    @Test
    fun `should retrieve the created customer`() {
        val requisition: HttpRequest<Cliente> = HttpRequest.GET(cliente?.id.toString())
        val body = streamingHttpClient.toBlocking().retrieve(requisition, Cliente::class.java)

        assertNotNull(body)
        assertEquals(cliente?.id, body.id)
        assertEquals(cliente?.nome, body.nome)
        assertTrue(cliente?.localizacao?.latitude?.compareTo(body.localizacao?.latitude) == 0)
        assertTrue(cliente?.localizacao?.longitude?.compareTo(body.localizacao?.longitude) == 0)
    }

    @Test
    fun `should test customer validation`() {
        val dto = ClienteDTO.builder().build()
        val requisition: HttpRequest<ClienteDTO> = HttpRequest.POST("/", dto)
        val exception = assertThrows(HttpClientResponseException::class.java) {
            streamingHttpClient.toBlocking().retrieve(requisition, Cliente::class.java)
        }

        assertEquals("Nome do cliente não pode estar vazio.", exception.message)
    }

    @Test
    fun `should validate latitude and longitude`() {
        val dto = ClienteDTO.builder().nome("FOOBAR").build()
        dto.localizacao = LocalizacaoDTO.builder().build()
        val requisition: HttpRequest<ClienteDTO> = HttpRequest.POST("/", dto)

        val exception = assertThrows(HttpClientResponseException::class.java) {
            streamingHttpClient.toBlocking().retrieve(requisition, Cliente::class.java)
        }

        assertTrue(exception.message?.contains("Informe a latitude.") ?: false)
        assertTrue(exception.message?.contains("Informe a longitude.") ?: false)
    }

    @Test
    fun `should throw an exception`() {
        val request: HttpRequest<Cliente> = HttpRequest.GET("573d4422-dd50-4c2a-ad84-89671c393ccc")
        val exception = assertThrows(
            HttpClientResponseException::class.java
        ) {
            streamingHttpClient.toBlocking().retrieve(request)
        }
        assertEquals("Nenhum Recurso encontrado.", exception.message)
    }
}