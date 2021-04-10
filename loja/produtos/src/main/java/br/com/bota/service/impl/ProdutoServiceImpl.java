package br.com.bota.service.impl;

import br.com.bota.entity.Produto;
import br.com.bota.repository.ProdutoRepository;
import br.com.bota.service.ProdutoService;
import br.com.bota.service.rule.ProdutoNotFoundException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ProdutoServiceImpl implements ProdutoService {
    @Inject
    private ProdutoRepository repository;

    @Transactional
    @Override
    public Produto salvar(Produto product) {
        if (product.getId() == null) {
            return repository.save(product);
        } else {
            var existente = this.procurarPorId(product.getId());
            existente.setDescricao(product.getDescricao());
            existente.setQuantidade(product.getQuantidade());
            existente.setValor(product.getValor());
            return repository.update(existente);
        }
    }

    @Transactional
    public void baixarEstoque(UUID id, Integer quantidade) {
        var produto = procurarPorId(id);
        log.info("Antes de baixar o estoque: {}", produto);
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        log.info("Depois de baixar o estoque: {}", produto);
        repository.update(produto);
    }

    @Override
    public Produto procurarPorId(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ProdutoNotFoundException());
    }

    @Override
    public Boolean verificarQuantidade(UUID id) {
        return this.procurarPorId(id).getQuantidade() > 0;
    }

    @Override
    public List<Produto> procurar() {
        return repository.findAll();
    }
}
