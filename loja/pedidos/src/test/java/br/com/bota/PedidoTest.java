package br.com.bota;

import br.com.bota.dto.ItemPedidoDTO;
import br.com.bota.dto.PedidoDTO;
import br.com.bota.repository.PedidoRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class PedidoTest {
    private static final String CLIENTE_ID = "e2676282-8126-4a23-863e-8a3bbcb4fab3";
    private static final String SMARTPHONE_ID = "53f2dbc0-179c-4b91-a231-8a8025de5bd2";
    private static final String LAPTOP_ID = "f5ae9761-7c8f-4ac3-aa8f-2b5b03961e93";
    private static final String TABLET_ID = "fc871373-616e-437d-af2c-bebca1dedbb2";

    @Inject
    @Client("/pedido")
    private RxHttpClient rxHttpClient;
    @Inject
    private PedidoRepository repository;

    @Test
    void createTest() {
        var pedido = new PedidoDTO();
        var smartphone = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(1499.99)).produtoId(UUID.fromString(SMARTPHONE_ID)).quantidade(3).build();
        var laptop = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(12600.0)).produtoId(UUID.fromString(LAPTOP_ID)).quantidade(2).build();
        var tablet = ItemPedidoDTO.builder().valor(BigDecimal.valueOf(2500.0)).produtoId(UUID.fromString(TABLET_ID)).quantidade(7).build();
        pedido.setItens(List.of(smartphone, laptop, tablet));
        pedido.setClienteId(UUID.fromString(CLIENTE_ID));
        HttpRequest<PedidoDTO> request = HttpRequest.POST("/", pedido);
        var body = rxHttpClient.toBlocking().retrieve(request, PedidoDTO.class);

        assertNotNull(body);
        assertTrue(BigDecimal.valueOf(47199.97).compareTo(body.getValorTotal()) == 0);
    }


}
