package br.com.bota.repository;

import br.com.bota.entity.Entrega;

import java.util.List;

public interface Repository {

    void salvar(Entrega entrega);

    Entrega entregar(Entrega entrega);

    List<Entrega> getPacotesNaoEntregues();
}
