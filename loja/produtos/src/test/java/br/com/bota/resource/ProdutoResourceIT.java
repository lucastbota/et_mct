package br.com.bota.resource;

import br.com.bota.entity.Produto;
import br.com.bota.repository.ProdutoRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ProdutoResourceIT {
    private static final String SMARTPHONE = "Smartphone";
    private static final Integer QUANTITY = 5;
    private static final BigDecimal VALUE = new BigDecimal(3000.00);
    @Inject
    @Client("/produto")
    private  RxHttpClient rxHttpClient;
    @Inject
    private  ProdutoRepository repository;
    private Produto product;


    @BeforeEach
    void setUp() {
        product = new Produto();
        product.setDescricao(SMARTPHONE);
        product.setQuantidade(QUANTITY);
        product.setValor(VALUE);
        product = repository.save(product);
    }

    @AfterEach
    void clean() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("Should retrieve the created product.")
    void testGetByValidId() {
        HttpRequest<Produto> requisition = HttpRequest.GET(product.getId().toString());
        var body = rxHttpClient.toBlocking().retrieve(requisition, Produto.class);

        assertNotNull(body);
        assertEquals(product.getId(), body.getId());
        assertEquals(SMARTPHONE, body.getDescricao());
        assertEquals(QUANTITY, body.getQuantidade());
        assertTrue(VALUE.compareTo(body.getValor()) == 0);
    }

    @Test
    @DisplayName("Should throw an exception")
    void testGetByInvalidId() {
        HttpRequest<Produto> request = HttpRequest.GET("/573d4422-dd50-4c2a-ad84-89671c393ccc");
        HttpClientResponseException exception = assertThrows(HttpClientResponseException.class, () -> rxHttpClient.toBlocking().retrieve(request));
        assertEquals("Nenhum Recurso encontrado", exception.getMessage());
    }
}
