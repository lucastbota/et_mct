package br.com.bota.event

import br.com.bota.converter.EntregaConverter
import br.com.bota.lojalib.dto.EntregaDTO
import br.com.bota.service.EntregaService
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.tracing.annotation.ContinueSpan
import io.micronaut.tracing.annotation.SpanTag
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@KafkaListener
open class RecebimentoPacoteEntregaKafkaListener @Inject constructor(private val entregaService: EntregaService) {
    val logger = LoggerFactory.getLogger(RecebimentoPacoteEntregaKafkaListener::class.java)

    @Topic("pedido_entrega")
    @ContinueSpan
    open fun receive(@SpanTag("entrega.recebida") @KafkaKey key: Long, dto: EntregaDTO) {
        logger.info("Evento recebido: {}-{}", key, dto)

        entregaService.criarEntrega(EntregaConverter.toEntrega(dto))
    }
}