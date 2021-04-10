package br.com.bota.entity.listener;

import br.com.bota.entity.Produto;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ProdutoListener {
    @PrePersist
    public void onSave(Produto product) {
        product.setCriadoEm(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(Produto product) {
        product.setModificadoEm(LocalDateTime.now());
    }
}
