package src;

import java.util.HashMap;
import java.util.Map;

public class Pedido {

    private String EnderecoEntrega;
    private Map<String, Integer> itensPedido;

    public Pedido(String enderecoEntrega, int[] itensPedido) {
        this.EnderecoEntrega = enderecoEntrega;
        this.itensPedido = new HashMap<>();
    }

    public void adicionaItemPedido(String codigoProduto, int quantidade){
        this.itensPedido.put(codigoProduto, quantidade);
    }

    public void removerItemPedido(String codigoProduto){
        this.itensPedido.remove(codigoProduto);
    }

    public int quantidadeItens(){
        return this.itensPedido.size();
    }


}
