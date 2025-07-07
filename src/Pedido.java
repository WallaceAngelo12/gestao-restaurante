package src;

import java.util.HashMap;
import java.util.Map;

public abstract class Pedido {

    private String codigoPedido;
    private int status;
    private Map<String, ItemPedido> itensPedido;

    public Pedido(String codigoPedido) {
        this.status = 0;
        this.codigoPedido = codigoPedido;
        this.itensPedido = new HashMap<>();
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public void adicionaItemPedido(ItemPedido item){
        itensPedido.put(item.getCodigoProduto(), item);
    }

    public void listarItens(){
        if (itensPedido.isEmpty()){
            System.out.println("Nenhum item no pedido.");
            return;
        }

        System.out.println("Itens do pedido:");

        for (ItemPedido item : itensPedido.values()) {
            System.out.println("Produto: " + item.getCodigoProduto());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.printf("Preço Unitário: R$ %.2f\n", item.getPrecoProduto());
            System.out.printf("Subtotal: R$ %.2f\n", item.calcularTotal());
            System.out.println("=========================");
        }
    }

    public void removerItemPedido(String codigoProduto){
       itensPedido.remove(codigoProduto);
    }


    public double calcularTotal(){
        double total = 0;
        for (ItemPedido item: itensPedido.values()) {
            total += item.calcularTotal();
        }
        return total;
    }

    //cada pedido vai demostrar a localização
    public abstract String getLocalizacao();

}
