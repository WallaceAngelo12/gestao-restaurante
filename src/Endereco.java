package src;

import java.io.BufferedReader;
import java.io.IOException;

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
    public void cadastrarEndereco(BufferedReader input) throws IOException {

        System.out.println("Informe o seu CEP:");
        this.cep = input.readLine();

        System.out.println("Informe o seu Bairro:");
        this.bairro = input.readLine();

        System.out.println("Informe o seu Complemento:");
        this.complemento = input.readLine();

        System.out.println("Informe o seu numero:");
        this.numero = input.readLine();

        System.out.println("Informa o seu Logradouro:");
        this.logradouro = input.readLine();

        System.out.println("Informe sua cidade");
        this.cidade = input.readLine();

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
