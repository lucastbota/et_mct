package br.com.bota.entity.listener;

import br.com.bota.entity.Cliente;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class CustomerListener {
    @PrePersist
    public void onSave(Cliente customer) {
        customer.setCriadoEm(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(Cliente customer) {
        customer.setModificadoEm(LocalDateTime.now());
    }
}
