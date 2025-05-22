import java.io.File;
import java.util.Scanner;

public class App {

    /**
     * Limpa a tela do console.
     */
    private static void limparTela() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }

    /**
     * Aguarda o usuário pressionar uma tecla para continuar.
     * @param scanner Scanner para ler a entrada do usuário.
     */
    private static void espera(Scanner scanner) {
        System.out.println("\n\nPressione qualquer tecla para continuar.");
        scanner.nextLine(); 
    }

    /**
     * Exibe o menu de opções para o usuário.
     * @param scanner Scanner para ler a entrada do usuário.
     * @param arvore ArvoreBST que contém as palavras indexadas.
     */
    private static void menu(Scanner scanner, ArvoreBST arvore) {
        String opcao;
        do {
            espera(scanner);
            limparTela();
            System.out.println("\n\n--- Menu ---");
            System.out.println("A arvore contém " + arvore.qtdNos() + " palavras unicas indexadas.");
            
            System.out.println("\n Escolha uma opção:");
            System.out.println("1. Listar palavras em ordem, com quantidade de ocorrências");
            System.out.println("2. Buscar palavra");
            System.out.println("3. Adicionar nova palavra");
            System.out.println("4. Remover palavra");
            System.out.println("5. Sair do programa");
            
            opcao = scanner.nextLine();
    
            switch (opcao) {
                case "1" -> {
                    System.out.println("Lista de todas as palavras no arquivo: ");
                    arvore.rastreioInordem();
                }
                case "2" -> {
                    No resultado = arvore.busca(scanner);
                    if (resultado == null) System.out.println("Palavra não encontrada.");
                }
                case "3" -> {
                    System.out.println("Digite a nova palavra que deseja adicionar:");
                    String novaPalavra = scanner.nextLine();
                    arvore.insere(novaPalavra);
                    System.out.println("Palavra '" + novaPalavra + "' adicionada com sucesso!");
                }
                case "4" -> {
                    System.out.println("Digite a palavra que deseja remover:");
                    String palavraRemover = scanner.nextLine();
                    No resultado = arvore.remove(palavraRemover);
                    if (resultado != null) System.out.println("A palavra '" + palavraRemover + "' foi removida com sucesso!");
                }
                case "5" -> {
                    System.out.println("Saindo do programa...");
                    return;
                }
            
                default -> {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } while (!opcao.equals("5"));

    }
    
    public static void main(String[] args) throws Exception {
        // Inicializar programa
        System.out.println("Bem-vindo ao indexador de palavras!"); 
        System.out.println("Primeiro, escreva o caminho absoluto do arquivo de texto que deseja indexar:");
        System.out.println("Exemplo: C:\\Users\\SeuUsuario\\Documents\\arquivo.txt \n(NECESSARIO ESCREVER O CAMINHO ABSOLUTO DE UM ARQUIVO TXT)");

        File arquivo;
        try (Scanner scanner = new Scanner(System.in)) {

            String caminho = scanner.nextLine();
            arquivo = new File(caminho);
            // Verifica se o caminho nos retorna um arquivo válido
            while (true) {
                
                if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) break;
                
                System.out.println("Caminho inválido. Digite 'C' para cancelar ou tente novamente:");
                caminho = scanner.nextLine();
                
                if (caminho.equalsIgnoreCase("C")) { // operação cancelada prematuramente.
                    System.out.println("Operação cancelada. Obrigado por usar o indexador de palavras!");
                    scanner.close();
                    return;
                }
            }
            
            // Criar BST e indexar palavras
            ArvoreBST arvore = new ArvoreBST();
            System.out.println("\n\nIndexando palavras... \n");
            
            try (Scanner leitor = new Scanner(arquivo)) {
                int count = 0;
                while (leitor.hasNext()) {
                    String palavra = leitor.next();
                    arvore.insere(palavra);
                    count++;
                    if (count % 100 == 0) {
                        System.out.println(count + " palavras indexadas até o momento...");
                    } 
                }
                leitor.close();
            } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                return;
            }
    
            System.out.println("Indexação concluída com sucesso!");


            // programa em execução
            menu(scanner, arvore);

            // final do programa
            System.out.println("Obrigado por usar o indexador de palavras!");
            espera(scanner);

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
