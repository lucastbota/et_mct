package br.com.bota.event;

import br.com.bota.dto.EntregaDTO;
import br.com.bota.service.EntregaService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

@Slf4j
@KafkaListener
public class RecebimentoPacoteEntregaKafkaListener {
    @Inject
    private EntregaService service;

    @Topic("pedido_entrega")
    public void receive(@KafkaKey long key, EntregaDTO entregaDTO) {
        log.info("Evento recebido: {}-{}", key, entregaDTO);
        service.criarEntrega(EntregaDTO.toDocument(entregaDTO));
    }
}
