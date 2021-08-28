package br.com.bota

import br.com.bota.entity.Produto
import br.com.bota.lojalib.dto.ProdutoDTO
import br.com.bota.repository.ProdutoRepository
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
class ProdutosTest {
    @Inject
    @field:Client("/produto") lateinit var streamingHttpClient: StreamingHttpClient
    @Inject
    lateinit var repositorio: ProdutoRepository
    private var produto: Produto = Produto(descricao = "Smartphone", quantidade = 5, valor = BigDecimal(3000.00))

    @BeforeEach
    fun setUp() {
        produto = repositorio.save(produto)
    }

    @AfterEach
    fun clean() =
        repositorio.deleteAll()

    @Test
    fun `should retrieve the created product`(){
        val requisition : HttpRequest<Produto> = HttpRequest.GET(produto.id?.toString())
        val body = streamingHttpClient.toBlocking().retrieve(requisition, Produto::class.java)

        assertNotNull(body)
        assertEquals(produto.id, body.id)
        assertEquals("Smartphone", body.descricao)
        assertEquals(5, body.quantidade)
        assertTrue(BigDecimal(3000.00).compareTo(body.valor) == 0)
    }

    @Test
    fun `should throw an exception` (){
         val request: HttpRequest<Produto> = HttpRequest.GET("/573d4422-dd50-4c2a-ad84-89671c393ccc")
        val exception: HttpClientResponseException = assertThrows(HttpClientResponseException::class.java) {
            streamingHttpClient.toBlocking().retrieve(request)
        }
        assertEquals("Nenhum Recurso encontrado", exception.message)
    }


    @Test
    fun `should test customer validation`() {
        val dto = ProdutoDTO()
        val requisition: HttpRequest<ProdutoDTO> = HttpRequest.POST("/", dto)
        val exception = assertThrows(HttpClientResponseException::class.java) {
            streamingHttpClient.toBlocking().retrieve(requisition)
        }
        val body = exception.response.getBody(String::class.java).orElse("")
        assertEquals("{\"message\":\"Bad Request\",\"_links\":{\"self\":{\"href\":\"/produto\",\"templated\":false}},\"_embedded\":{\"errors\":[{\"message\":\"O Produto deve ter uma descrição.\"}]}}", body)
    }
}