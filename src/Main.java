package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            Endereco endereco = new Endereco();

            endereco.cadastrarEndereco(input);
            System.out.println(endereco.enderecoReturn());
            System.out.println("Hello World!");


    }
}
