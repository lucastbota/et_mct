package br.com.bota.resource;

import br.com.bota.dto.ClienteDTO;
import br.com.bota.dto.LocalizacaoDTO;
import br.com.bota.entity.Cliente;
import br.com.bota.entity.Localizacao;
import br.com.bota.repository.ClienteRepository;
import br.com.bota.rule.IntegrationRule;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(IntegrationRule.class)
@MicronautTest
class ClienteResourceIT {
    @Inject
    @Client("/cliente")
    private RxHttpClient rxHttpClient;
    @Inject
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        var local = new Localizacao();
        local.setLongitude(BigDecimal.valueOf(-49.3815748));
        local.setLatitude(BigDecimal.valueOf(-20.8168906));
        cliente = new Cliente();
        cliente.setNome("Teresa do sorriso aparente");
        cliente.setLocalizacao(local);
        local.setCliente(cliente);
        cliente = clienteRepository.save(cliente);
    }

    @AfterEach
    void clean() {
        var clientes = clienteRepository.findAll();
        clienteRepository.deleteAll(clientes);
    }


    @Test
    @DisplayName("Should retrieve the created customer.")
    void testGetByValidId() {
        HttpRequest<Cliente> requisition = HttpRequest.GET(cliente.getId().toString());
        var body = rxHttpClient.toBlocking().retrieve(requisition, Cliente.class);

        assertNotNull(body);
        assertEquals(cliente.getId(), body.getId());
        assertEquals(cliente.getNome(), body.getNome());
        assertTrue(cliente.getLocalizacao().getLatitude().compareTo(body.getLocalizacao().getLatitude()) == 0);
        assertTrue(cliente.getLocalizacao().getLongitude().compareTo(body.getLocalizacao().getLongitude()) == 0);
    }

    @Test
    @DisplayName("Should ")
    void testCustomerValidation() {
        var dto = ClienteDTO.builder().build();
        HttpRequest<ClienteDTO> requisition = HttpRequest.POST("/", dto);
        var exception = assertThrows(HttpClientResponseException.class, () -> rxHttpClient.toBlocking().retrieve(requisition, Cliente.class));
        assertEquals("Nome do cliente não pode estar vazio.", exception.getMessage());
    }

    @Test
    @DisplayName("Should validate latitude and longitude")
    void testCustomerAddressValidation() {
        var dto = ClienteDTO.builder().nome("FOOBAR").build();
        dto.setLocalizacao(LocalizacaoDTO.builder().build());
        HttpRequest<ClienteDTO> requisition = HttpRequest.POST("/", dto);

        var exception = assertThrows(HttpClientResponseException.class, () -> rxHttpClient.toBlocking().retrieve(requisition, Cliente.class));
        assertTrue(exception.getMessage().contains("Informe a latitude."));
        assertTrue(exception.getMessage().contains("Informe a longitude."));
    }

    @Test
    @DisplayName("Should throw an exception")
    void testGetByInvalidId() {
        HttpRequest<Cliente> request = HttpRequest.GET("573d4422-dd50-4c2a-ad84-89671c393ccc");
        var exception = assertThrows(HttpClientResponseException.class, () -> rxHttpClient.toBlocking().retrieve(request));
        assertEquals("Nenhum Recurso encontrado", exception.getMessage());
    }
}
