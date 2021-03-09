package br.com.bota.service.rule;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException() {
        super("Pedido não encontrado");
    }
}
