package br.com.bota.service;

import br.com.bota.entity.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    Produto salvar(Produto product);
    void baixarEstoque(UUID id, Integer quantidade);
    Produto procurarPorId(UUID id);
    Boolean verificarQuantidade(UUID id);
    List<Produto> procurar();
}
