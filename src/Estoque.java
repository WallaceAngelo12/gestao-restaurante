package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Estoque {
        private Map<String, Produto> produtos = new HashMap<>();

        public void cadastrarProduto(Scanner input){
            System.out.println("Digite o código do produto: ");
            String codigo = input.nextLine();

            if (produtos.containsKey(codigo)){
                System.out.println("Ja existe um produto cadastrado com esse código! Cadastro cancelado.");
                return;
            }
            Produto p = new Produto();
            p.setCodigoProduto(codigo);
            p.preencherProduto(input);

            System.out.println("Código lido: " + codigo);
            System.out.println("Código no produto: " + p.getCodigoProduto());


            produtos.put(codigo, p);
            System.out.println("Produto cadastrado com sucesso!");
        }

        public Produto buscarProduto(String codigo){
            return  produtos.get(codigo);
        }

        public boolean removerProduto(String codigo){
            if(produtos.containsKey(codigo)){
                Produto p = produtos.remove(codigo);
                System.out.println("Produto"+ p.getDescricaoProduto() + " removido com sucesso!");
                return true;
            }else{
                System.out.println("Produto não encontrado.");
                return false;
            }
        }

        public void listarTodosProdutos(){
            if(produtos.isEmpty()){
                System.out.println("O estoque está vazio");
            }else{
                System.out.println("Lista de produtos no estoque: ");
                for(Produto produto : produtos.values()){
                    System.out.println("----------------------");
                    System.out.println("Código: " + produto.getCodigoProduto());
                    System.out.println("Descrição: " + produto.getDescricaoProduto());
                    System.out.println("Valor: " + produto.getValor());
                    System.out.println("Validade: " +  produto.getValidade().format(produto.formatter));

                }
                System.out.println("----------------------");
            }
        }

        public void listarProdutos(Scanner input){
            if(produtos.isEmpty()){
                System.out.println("O estoque está Vazio");
                return;
            }
            System.out.println("Deseja filtrar por categoria? (s/n): ");
            String resposta = input.nextLine().trim().toLowerCase();

            if (resposta.equals("s")){
                System.out.println("Digite a categoria(B - Bebida, C - Comida, S - Sobremesa): ");
                String letraCategoria = input.nextLine().trim().toUpperCase();

                String categoria;
                switch (letraCategoria) {
                    case "B": categoria = "Bebida"; break;
                    case "C": categoria = "Comida"; break;
                    case "S": categoria = "Sobremesa"; break;
                    default: categoria = "Outros"; break;
                }

                listarPorCategoria(categoria);
            }else {
                listarTodosProdutos();
            }

        }


        public void listarPorCategoria(String categoriaProduto){
           boolean encontrou = false;

           System.out.println("=== Produtos da Categoria: " + categoriaProduto + " ===");

           for (Produto p  : produtos.values()){
               String categoriaDoProduto = getCategoriaDoCodigo(p.getCodigoProduto());

               if(categoriaDoProduto.equalsIgnoreCase(categoriaProduto)){
                   System.out.println(p.getCodigoProduto()+ " - " + p.getDescricaoProduto()+ " - R$" + p.getValor());
                   encontrou = true;
               }
           }

        if (!encontrou){
            System.out.println("Nenhum produto foi encontrado nesta categoria!");
         }
    }

    private String getCategoriaDoCodigo(String codigo){
            if(codigo == null || codigo.isEmpty()){
                return "Outros";
            }

            char letra = Character.toUpperCase(codigo.charAt(0));
            // Aqui pega a primeira letra do codigo
            switch(letra){
                case 'B': return "Bebida";
                case 'C': return  "Comida";
                case 'S': return "Sobremesa";
                default: return "Outros";

        }
    }
}
