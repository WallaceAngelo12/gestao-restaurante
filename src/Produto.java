package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Produto {

    private String codigoProduto;
    private String descricaoProduto;
    private float valor;
    private LocalDate validade;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Produto(){
        this.validade = LocalDate.now();
    }

    public Produto(String codigoProduto, String descricaoProduto, float valor) {
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        this.valor = valor;
        this.validade = LocalDate.now();
    }


    // Getters e Setters
    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        valor = valor;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void preencherProduto(BufferedReader input) throws IOException {


        while (true) {
            System.out.println("Digite o valor do produto: ");
            String entrada = input.readLine().replace(",",".");


            try{
                this.valor = Float.parseFloat(entrada);
                break;
            } catch(NumberFormatException e){
                System.out.println("Valor inválido! Digite apenas números!");
            }
        }


        System.out.println("Digite a descricao do produto: ");
        this.descricaoProduto = input.readLine();


        while(true){
            System.out.println("Digite o validade do produto: ");
            String entradaData = input.readLine();
            try{
                this.validade = LocalDate.parse(entradaData, formatter);
                break;
            }catch(DateTimeParseException e){
                System.out.println("Data inválida! Tente novamente");
            }
        }
    }
}
