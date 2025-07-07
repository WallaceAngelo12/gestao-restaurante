package src;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Estoque {
        private Map<String, Produto> produtos = new HashMap<>();
        private final DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        public void cadastrarProduto(BufferedReader input) throws IOException{
            System.out.println("Digite o código do produto: ");
            String codigo = input.readLine();

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

    public void salvarProdutosEmArquivo(String caminhoArquivo){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))){
                for (Produto produto : produtos.values()){
                    String linha = produto.getCodigoProduto() + ";" + produto.getDescricaoProduto() + ";" + produto.getValor() + ";" + produto.getValidade().format(produto.formatter);
                    writer.write(linha);
                    writer.newLine();
                }
                System.out.println("Produtos salvos com sucesso!");
            }catch(IOException e){
                System.out.println("Erro ao salvar protudos: " + e.getMessage());
            }
    }

    public void carregarProdutosDoArquivo(String caminhoArquivo){
            File arquivo = new File(caminhoArquivo);

            if (!arquivo.exists()){
                System.out.println("Arquivo de produtos não encontrado, inicinado estoque vazio.");
            try {
                arquivo.createNewFile();
        }catch (IOException e){
                System.out.println("Erro ao carregar arquivo de produtos: " + e.getMessage());
        }
            return;
        }
            try(BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))){
                String linha;
                while((linha = reader.readLine()) != null){
                    String[] partes = linha.split(";");
                    if(partes.length == 4){
                        String codigoProduto = partes[0];
                        String descricaoProduto = partes[1];
                        float valor = Float.parseFloat(partes[2]);
                        LocalDate validade = LocalDate.parse(partes[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Produto produto = new Produto(codigoProduto, descricaoProduto, valor);
                        produtos.put(codigoProduto, produto);
                    }
                }
                System.out.println("Produtos carregados com sucesso!");
            }catch (IOException e) {
                System.out.println("Erro ao carregar produtos: " + e.getMessage());
            }
    }
}
