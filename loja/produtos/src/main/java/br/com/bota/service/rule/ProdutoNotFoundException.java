package br.com.bota.service.rule;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException() {
        super("Nenhum Recurso encontrado");
    }
}
