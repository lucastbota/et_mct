package br.com.bota.service

import br.com.bota.entity.Entrega
import br.com.bota.repository.Repository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.time.LocalDateTime
import java.util.*

interface EntregaService {
    fun criarEntrega(entrega: Entrega)
    fun confirmarEntrega(): List<Entrega>
}

@Singleton
class EntregaServiceImpl @Inject constructor(private val repository: Repository) : EntregaService {
    private val log = LoggerFactory.getLogger(EntregaServiceImpl::class.java)
    override fun criarEntrega(entrega: Entrega) {
        log.info("Armazenando o evento...")
        entrega.pacoteRecebidoEm = LocalDateTime.now().toString()
        entrega.entregou = false
        repository.salvar(entrega)
    }

    override fun confirmarEntrega(): List<Entrega> {
        val result = repository.getPacotesNaoEntregues()
        log.info("Qtd pacotes não entregues: {}", result.size)

        return result.map {
            it.entregaRealizadaEm = LocalDateTime.now().toString()
            it.assinaturaCliente = it.entregaRealizadaEm.hashCode().toString()
            it.documentoCliente = Random().nextDouble().toString()
            it.entregou = true
            repository.entregar(it)
        }
    }
}