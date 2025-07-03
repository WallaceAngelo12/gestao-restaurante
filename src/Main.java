package src;

import java.util.Scanner;

public class Main {

    private static Estoque estoque = new Estoque();
    private static Endereco endereco = new Endereco();
    private static Pedido pedidoAtual = null;

    public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            while(true){
                System.out.println("=== Menu Principal ====");
                System.out.println("1 - Controle de vendas");
                System.out.println("2 - Gerenciar Produtos");
                System.out.println("0 - Sair");
                System.out.println("Digite uma opcao: ");

                int opcao = input.nextInt();
                input.nextLine();

                switch(opcao){
                    case 1:
                        menuPedido(input);
                        break;
                    case 2:
                        menuProdutos(input);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Opcao invalida");
                }
            }
}

    private static void menuPedido(Scanner input){
        while(true){
            System.out.println("=== Menu Pedidos ====");
            System.out.println("1 - Abrir novo Pedido");
            System.out.println("2 - Adicionar item ao pedido");
            System.out.println("3 - Remover item do pedido");
            System.out.println("4 - listar itens do pedido");
            System.out.println("5 - Mostrar endereço do pedido");
            System.out.println("6 - Calcular total do pedido");
            System.out.println("0 - voltar ao menu principal");
            System.out.println("Digite uma opcao: ");

            String opcao = input.next();

            switch(opcao){
                case "1":
                    abrirPedido(input);
                    break;
                case "2":
                    adicionarItem(input);
                    break;
                case "3":
                    removerItem(input);
                    break;
                case "4":
                    listarItens();
                    break;
                case "5":
                    mostrarEndereco();
                    break;
                case "6":
                    calcularTotal();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private static void menuProdutos(Scanner input) {

        while(true){

            System.out.println("======= MENU Produtos =======");
            System.out.println("1 - cadastrar novo produto");
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
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }


    }

    private static void abrirPedido(Scanner input) {
        Endereco endereco = new Endereco();
        System.out.println("cadastrar endereço do pedido:");
        endereco.cadastrarEndereco(input);

         System.out.println("Digite o codigo do Pedido: ");
         String codigo = input.nextLine();

         pedidoAtual = new Pedido(endereco, codigo);
         System.out.println("Pedido criado com sucesso!");
    }

    private static void adicionarItem(Scanner input){
        if(pedidoAtual == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }

        System.out.println("Digite o codigo do Produto: ");
        String codigoProduto = input.nextLine();
        System.out.println("DIgite a quantidade: ");
        int quantidade = Integer.parseInt(input.nextLine());
        System.out.println("Digite o valor do Produto: ");
        double precoProduto = input.nextDouble();

        ItemPedido item = new ItemPedido(codigoProduto, quantidade, precoProduto, 0);
        pedidoAtual.adicionaItemPedido(item);
        System.out.println("Item criado com sucesso!");
    }

    private static void removerItem(Scanner input){
        if(pedidoAtual == null){
            System.out.println("você precisa abrir um pedido primeiro!");
            return;
        }

        System.out.println("Digite o codigo do Produto: ");
        String codigoProduto = input.nextLine();
        pedidoAtual.removerItemPedido(codigoProduto);
        System.out.println("Item removido com sucesso!");
    }

    private static void listarItens(){
        if(pedidoAtual == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }
        pedidoAtual.listarItens();
    }

    private static void mostrarEndereco(){
        if(pedidoAtual == null){
            System.out.println("Você precis abrir um pedido primeiro!");
            return;
        }
        System.out.println("Endereço de entrega: ");
        System.out.println(pedidoAtual.getenderecoEntrega().enderecoReturn());
    }

    private static void calcularTotal(){
        if(pedidoAtual == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }

        double total = pedidoAtual.calcularTotal();
        System.out.println("Total do pedido: " + total);
    }
}


