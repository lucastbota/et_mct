package br.com.bota.service.impl;

import br.com.bota.entity.Entrega;
import br.com.bota.repository.Repository;
import br.com.bota.service.EntregaService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Slf4j
public class EntregaServiceImpl implements EntregaService {
    @Inject
    private Repository repository;

    @Override
    public void criarEntrega(Entrega entrega) {
        log.info("Armazenando o evento...");
        entrega.setPacoteRecebidoEm(LocalDateTime.now().toString());
        entrega.setEntregou(Boolean.FALSE);
        repository.salvar(entrega);
    }

    @Override
    public List<Entrega> confirmarEntrega() {
        var result = repository.getPacotesNaoEntregues();
        log.info("Qtd pacotes não entregues: {}", result.size());

        return result.stream().map(realizarEntrega()).map(repository::entregar).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public UnaryOperator<Entrega> realizarEntrega() {
        return e -> {
            e.setEntregaRealizadaEm(LocalDateTime.now().toString());
            e.setAssinaturaCliente(String.valueOf(e.getEntregaRealizadaEm().hashCode()));
            e.setDocumentoCliente(String.valueOf(new Random().nextDouble()));
            e.setEntregou(Boolean.TRUE);
            return e;
        };
    }
}
