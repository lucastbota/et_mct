package br.com.bota.service.impl;

import br.com.bota.entity.Cliente;
import br.com.bota.repository.ClienteRepository;
import br.com.bota.service.ClienteService;
import br.com.bota.service.rule.CustomerNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Singleton
public class ClienteServiceImpl implements ClienteService {
    @Inject
    private ClienteRepository repository;


    @Transactional
    @Override
    public Cliente save(
            @Valid Cliente customer) {
        if (customer.getId() == null) {
            return repository.save(customer);
        } else {
            var existent = repository.findById(customer.getId());
            existent.ifPresent(c -> c.setNome(customer.getNome()));
            return customer;
        }
    }

    @Override
    public Cliente findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException());
    }

    @Override
    public List<Cliente> findAll() {
        return repository.findAll();
    }
}
