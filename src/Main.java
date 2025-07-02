package src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            Endereco endereco = new Endereco();
            Estoque estoque = new Estoque();

            while(true){

                System.out.println("======= MENU Principal =======");

                System.out.println("1 - Cadastrar produto");
                System.out.println("2 - Remover produto");
                System.out.println("3 - Listar produtos");
                System.out.println("4 - cadastrar Endereço");
                System.out.println("0 - Sair");

                String opcao = input.next();

                input.nextLine();


                switch(opcao){
                    case "1":
                        estoque.cadastrarProduto(input);
                        break;
                    case "2":
                        System.out.println("Digite o codigo do Produto: ");
                        String codigoRemove = input.nextLine();
                        estoque.removerProduto(codigoRemove);
                        break;
                    case "3":
                        estoque.listarTodosProdutos();
                        break;
                    case "4":
                        endereco.cadastrarEndereco(input);
                        System.out.println(endereco.enderecoReturn());
                        break;
                    case "0":
                        System.out.println("Saindo...");
                        input.close();
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            }


    }
}
