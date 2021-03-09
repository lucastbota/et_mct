package br.com.bota.service;

import br.com.bota.entity.Entrega;

import java.util.List;

public interface EntregaService {
    void criarEntrega(Entrega entrega);

    List<Entrega> confirmarEntrega();


}
