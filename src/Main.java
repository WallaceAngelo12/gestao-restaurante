package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Estoque estoque = new Estoque();
    private static Map<String, Pedido> pedidos = new HashMap();
    private static String pedidoSelecionado = null;
    private static int contadorPedidos = 1;
    private static final int limiteMesas = 20;
    private static final String caminhoArquivo = "src/data/produtos.txt";

    public static void main(String[] args) throws IOException {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            //carregar os produtos
            estoque.carregarProdutosDoArquivo(caminhoArquivo);

            while(true){
                System.out.println("=== Menu Principal ====");
                System.out.println("1 - Controle de vendas");
                System.out.println("2 - Gerenciar Produtos");
                System.out.println("0 - Sair");
                System.out.println("Digite uma opcao: ");

                String opcao = input.readLine();

                switch(opcao){
                    case "1":
                        menuPedido(input);
                        break;
                    case "2":
                        menuProdutos(input);
                        break;
                    case "0":
                        System.out.println("Saindo...");
                        input.close();
                        System.exit(0);
                    default:
                        System.out.println("Opcao invalida");
                }
            }
}

    private static void menuPedido(BufferedReader input) throws IOException {
        while(true){
            System.out.println("=== Menu Pedidos ====");
            System.out.println("1 - Abrir novo Pedido Online");
            System.out.println("2 - Abrir novo Pedido de Mesa");
            System.out.println("3 - Adicionar item ao pedido");
            System.out.println("4 - Remover item do pedido");
            System.out.println("5 - listar itens do pedido");
            System.out.println("6 - Mostrar local de entrega/mesa");
            System.out.println("7 - Calcular total do pedido");
            System.out.println("8 - finalizar pedido e emitir nota fiscal");
            System.out.println("9 - selecionar pedido existente");
            System.out.println("10 - listar todos os pedidos ativos");
            System.out.println("0 - voltar ao menu principal");
            System.out.println("Digite uma opcao: ");

            String opcao = input.readLine();

            switch(opcao){
                case "1":
                    abrirPedidoOnline(input);
                    break;
                case "2":
                    abrirPedidoMesa(input);
                    break;
                case "3":
                    adicionarItem(input);
                    break;
                case "4":
                    removerItem(input);
                    break;
                case "5":
                    listarItens();
                    break;
                case "6":
                    mostrarLocalizacao();
                    break;
                case "7":
                    calcularTotal();
                    break;
                case "8":
                    finalizarPedido();
                    break;
                case "9":
                    selecionarPedido(input);
                    break;
                case "10":
                    listarTodosPedidos();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opcao invalida");
            }
        }
    }

    private static void menuProdutos(BufferedReader input) throws IOException {

        while(true){

            System.out.println("======= MENU Produtos =======");
            System.out.println("1 - cadastrar novo produto");
            System.out.println("2 - Remover produto");
            System.out.println("3 - Listar produtos");
            System.out.println("0 - Sair");

            String opcao = input.readLine();


            switch(opcao){
                case "1":
                    estoque.cadastrarProduto(input);
                    estoque.salvarProdutosEmArquivo(caminhoArquivo);//salva os produto
                    break;
                case "2":
                    System.out.println("Digite o codigo do Produto: ");
                    String codigoRemove = input.readLine();
                    if(estoque.removerProduto(codigoRemove)){
                        estoque.salvarProdutosEmArquivo(caminhoArquivo);
                    }
                    break;
                case "3":
                    estoque.listarTodosProdutos();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }


    }

    private static void abrirPedidoOnline(BufferedReader input) throws IOException {
        Endereco endereco = new Endereco();
        System.out.println("cadastrar endereço do pedido:");
        endereco.cadastrarEndereco(input);

         String codigo = "ONLINE" + contadorPedidos++;
         Pedido pedido = new PedidoOnline(codigo, endereco);

         pedidos.put(codigo,pedido);
         pedidoSelecionado = codigo;

         System.out.println("Pedido criado com sucesso!" + codigo);
    }

    private static void abrirPedidoMesa(BufferedReader input) throws IOException {

        System.out.println("Digite o número da mesa: ");
        int numeroMesa = Integer.parseInt(input.readLine());

        if(numeroMesa< 1 || numeroMesa>limiteMesas){
            System.out.println("Número de mesa inválido! Escolhe de 1 a "+limiteMesas);
            return;
        }

        String codigo = "MESA" + numeroMesa;
        if(pedidos.containsKey(codigo)){
            System.out.println("Já existe um pedido ativo para a mesa" + numeroMesa);
            return;
        }

        Mesa mesa = new Mesa(numeroMesa);
        Pedido pedido = new PedidoMesa(codigo, mesa);

        pedidos.put(codigo, pedido);
        pedidoSelecionado = codigo;

        System.out.println("Pedido criado com sucesso!" + codigo);
    }

    private static void selecionarPedido(BufferedReader input) throws IOException {
        if(pedidos.isEmpty()){
            System.out.println("Nenhum pedido cadastrado!");
            return;
        }

        System.out.println("Pedidos disponiveis: ");
        for(String cod: pedidos.keySet()){
            System.out.println("- " + cod);
        }

        System.out.println("Digite o codigo do Pedido: ");
        String codigo = input.readLine().trim();

        if (pedidos.containsKey(codigo)){
            pedidoSelecionado = codigo;
            System.out.println("Pedido" + codigo + "selecionado.");
        }else{
            System.out.println("Pedido não encontrado!");
        }
    }

    private static Pedido getPedidoAtual(){
        if (pedidoSelecionado == null || !pedidos.containsKey(pedidoSelecionado)){
            System.out.println("Nenhum pedido selecionado!");
            return null;
        }
        return pedidos.get(pedidoSelecionado);
    }

    private static void adicionarItem(BufferedReader input) throws IOException {
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }

        System.out.println("Digite o codigo do Produto: ");
        String codigoProduto = input.readLine();

        Produto prod = estoque.buscarProduto(codigoProduto);
        if(prod == null){
            System.out.println("Produto não encontrado no estoque!");
            return;
        }



        System.out.println("Digite a quantidade: ");
        int quantidade = Integer.parseInt(input.readLine());

        float precoProduto = prod.getValor();

        ItemPedido item = new ItemPedido(codigoProduto, quantidade, precoProduto, 0);
        pedido.adicionaItemPedido(item);
        System.out.println("Item criado com sucesso!");
    }

    private static void removerItem(BufferedReader input) throws IOException {
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("você precisa abrir um pedido primeiro!");
            return;
        }

        System.out.println("Digite o codigo do Produto: ");
        String codigoProduto = input.readLine();
        pedido.removerItemPedido(codigoProduto);
        System.out.println("Item removido com sucesso!");
    }

    private static void listarItens(){
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }
        pedido.listarItens();
    }

    private static void mostrarLocalizacao(){
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("Você precis abrir um pedido primeiro!");
            return;
        }
        System.out.println("Endereço de entrega: ");
        System.out.println(pedido.getLocalizacao());
    }

    private static void calcularTotal(){
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }

        double total = pedido.calcularTotal();
        System.out.printf("Total do pedido: R$ %.2f%n ", total);
    }

    private static void finalizarPedido(){
        Pedido pedido = getPedidoAtual();
        if(pedido == null){
            System.out.println("Você precisa abrir um pedido primeiro!");
            return;
        }

        double total = pedido.calcularTotal();

        String numeroNota = "NF" + System.currentTimeMillis();

        NotaFiscal nota = new NotaFiscal(numeroNota,(float) total);
        nota.emitirNotaFiscal();

        pedidos.remove(pedidoSelecionado);
        pedidoSelecionado = null;

        System.out.println("Pedido finalizado e nota fiscal emitida.");
    }

    private static void listarTodosPedidos(){
        if(pedidos.isEmpty()){
            System.out.println("Nenhum pedido selecionado!");
            return;
        }

        System.out.println("Pedidos ativos: ");
        for(String codigo : pedidos.keySet()){
            System.out.println("-" + codigo);
        }
    }
}


