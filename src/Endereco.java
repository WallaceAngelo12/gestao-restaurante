package src;

import java.util.Scanner;

public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String cep;

    public Endereco() {
    }

    // Getters e Setters
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    // O Scanner será chamado na classe principal
    public void cadastrarEndereco(Scanner input){

        System.out.println("Informe o seu CEP:");
        this.cep = input.nextLine();

        System.out.println("Informe o seu Bairro:");
        this.bairro = input.nextLine();

        System.out.println("Informe o seu Complemento:");
        this.complemento = input.nextLine();

        System.out.println("Informe o seu numero:");
        this.numero = input.nextLine();

        System.out.println("Informa o seu Logradouro:");
        this.logradouro = input.nextLine();

        System.out.println("Informe sua cidade");
        this.cidade = input.nextLine();

    }

    public String enderecoReturn(){
        return "Logradouro: " + logradouro + "\n" +
                "Número: " + numero + "\n" +
                "Complemento: " + complemento + "\n" +
                "Bairro: " + bairro + "\n" +
                "Cidade: " + cidade + "\n" +
                "CEP: " + cep;
    }
}
