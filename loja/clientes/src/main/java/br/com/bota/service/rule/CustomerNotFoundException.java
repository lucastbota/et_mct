package br.com.bota.service.rule;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Nenhum Recurso encontrado");
    }
}
