const https = require('https');

const clienteAPI = require('./cliente-api');
const produtoAPI = require('./produto-api');
const pedidoAPI = require('./pedido-api');
const clientes = require('./data/clientes.json');
const produtos = require('./data/produtos.json');

 
clientes.forEach(e => clienteAPI.create(JSON.stringify(e)));

produtos.forEach(e => produtoAPI.create(JSON.stringify(e)));


for (let i = 0; i < 3; i++) {
   // createPedido();
}


function createPedido() {
    clienteAPI.get(function (dataCliente) {
        var arrCliente = JSON.parse(dataCliente);
        produtoAPI.get(function (dataProduto) {
            var arrProduto = JSON.parse(dataProduto);
            arrCliente.forEach(c => {
                const itens = [];

                arrProduto.forEach(p => {
                    itens.push({
                        produtoId: p.id,
                        valor: p.valor,
                        quantidade: Math.floor(Math.random() * 10) + 1
                    });
                });

                var pedido = {
                    clienteId: c.id,
                    itens: itens
                };

                pedidoAPI.create(JSON.stringify(pedido));
            });
        });
    });
    ;
}