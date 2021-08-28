package br.com.bota.lojalib.enums;

public enum PedidoStatusEnum {
    AGUARDANDO_PAGAMENTO {
        @Override
        public PedidoStatusEnum next() {
            return PAGAMENTO_APROVADO;
        }
    },

    PAGAMENTO_APROVADO {
        @Override
        public PedidoStatusEnum next() {
            return PEDIDO_SEPARADO_TRANSPORTADORA;
        }
    },

    PEDIDO_SEPARADO_TRANSPORTADORA {
        @Override
        public PedidoStatusEnum next() {
            return PEDIDO_ENTREGUE_TRANSPORTADORA;
        }
    },

    PEDIDO_ENTREGUE_TRANSPORTADORA {
        @Override
        public PedidoStatusEnum next() {
            return PEDIDO_ENTREGUE;
        }
    },

    PEDIDO_ENTREGUE {
        @Override
        public PedidoStatusEnum next() {
            return null;
        }
    },

    CANCELADO {
        @Override
        public PedidoStatusEnum next() {
            return null;
        }
    };

    public abstract PedidoStatusEnum next();
}
