package br.com.bota.service;

import br.com.bota.entity.Cliente;

import java.util.List;
import java.util.UUID;

public interface ClienteService {
    Cliente save(Cliente customer);
    Cliente findById(UUID id);
    List<Cliente> findAll();
}
