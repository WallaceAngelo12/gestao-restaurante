package src;

import java.util.Scanner;

public class ItemPedido {

    private String codigoProduto;
    private int quantidade;
    private double precoProduto;
    private int status;


    public ItemPedido(String codigoProduto, int quantidade, double precoProduto, int status) {
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.precoProduto = precoProduto;
        this.status = status;
    }

    // Getters e setters
    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void  setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

        public double calcularTotal(){
            return quantidade * precoProduto;
        }
    }



