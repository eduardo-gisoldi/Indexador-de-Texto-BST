import java.io.File;
import java.util.Scanner;

/**
 * Classe principal do programa de indexação de palavras.
 * Este programa lê um arquivo de texto, indexa as palavras em uma árvore binária de busca (BST).
 * Permite ao usuário realizar operações como listar palavras, buscar, adicionar e remover palavras.
 */
public class App {

    /**
     * Limpa a tela do console no Windows. Caso não seja possível, imprime várias linhas em branco.
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
     * Exibe e controla o menu de opções para o usuário.
     * @param scanner Scanner para ler a entrada do usuário.
     * @param arvore ArvoreBST que contém as palavras indexadas.
     */
    private static void menu(Scanner scanner, ArvoreBST arvore) {
        String opcao;
        do {
            espera(scanner);
            limparTela();
            System.out.println("\n\n--- Menu ---");
            System.out.println("A arvore contem " + arvore.qtdNos() + " palavras unicas indexadas.");
            
            System.out.println("\n Escolha uma opcao:");
            System.out.println("1. Listar palavras em ordem, com quantidade de ocorrencias");
            System.out.println("2. Buscar palavra");
            System.out.println("3. Adicionar nova palavra");
            System.out.println("4. Remover palavra");
            System.out.println("5. Sair do programa");
            
            opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    System.out.println("Lista de todas as palavras no arquivo:");
                    arvore.rastreioInordem();
                    break;
                case "2":
                    No resultado = arvore.busca(scanner);
                    if (resultado == null) System.out.println("Palavra nao encontrada.");
                    break;
                case "3":
                    System.out.println("Digite a nova palavra que deseja adicionar:");
                    String novaPalavra = scanner.nextLine();
                    No result = arvore.insere(novaPalavra);
                    // Mensagem de sucesso já é exibida pelo método insere
                    break;
                case "4":
                    System.out.println("Digite a palavra que deseja remover:");
                    String palavraRemover = scanner.nextLine();
                    No resultadoRemover = arvore.remove(palavraRemover);
                    if (resultadoRemover != null) System.out.println("A palavra '" + palavraRemover + "' foi removida com sucesso!");
                    break;
                case "5":
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        } while (!opcao.equals("5"));
    }
    
    /**
     * Método principal que inicia o programa.
     * Solicita ao usuário o caminho do arquivo a ser indexado e executa as operações de indexação.
     * @param args Argumentos da linha de comando (não utilizados).
     * @throws Exception Se ocorrer um erro ao ler o arquivo.
     */
    public static void main(String[] args) throws Exception {
        // Inicializar programa
        System.out.println("Bem-vindo ao indexador de palavras!"); 
        System.out.println("Primeiro, escreva o caminho absoluto do arquivo de texto que deseja indexar:");
        System.out.println("Exemplo: C:\\Users\\SeuUsuario\\Documents\\arquivo.txt \n(NECESSARIO ESCREVER O CAMINHO ABSOLUTO DE UM ARQUIVO TXT)");

        File arquivo;
        try (Scanner scanner = new Scanner(System.in)) {

            // Solicitar caminho do arquivo até que seja válido ou seja cancelado
            String caminho;
            boolean caminhoValido = false;
            do {
                caminho = scanner.nextLine();
                if (caminho.equalsIgnoreCase("C")) {
                    System.out.println("Operacao cancelada. Obrigado por usar o indexador de palavras!");
                    return;
                }
                arquivo = new File(caminho);
                if (arquivo.exists() && arquivo.isFile() && arquivo.canRead()) {
                    caminhoValido = true;
                } else {
                    System.out.println("Caminho invalido. Digite 'C' para cancelar ou tente novamente:");
                }
            } while (!caminhoValido);
            
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
                        System.out.println(count + " palavras indexadas ate o momento...");
                    } 
                }
            } catch (Exception e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                return;
            }
    
            System.out.println("Indexacao concluida com sucesso!");


            // Programa em execução
            menu(scanner, arvore);

            // Final do programa
            System.out.println("Obrigado por usar o indexador de palavras!");
            espera(scanner);

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
