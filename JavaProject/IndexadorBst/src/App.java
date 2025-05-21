import java.io.File;
import java.util.Scanner;

public class App {

    private static void menu() {
        System.out.println("Otimo, agora escolha uma opção:");
        System.out.println("1. Buscar palavra inteira");
        System.out.println("2. Buscar palavra por substring");
        System.out.println("3. Adicionar nova palavra");
        System.out.println("4. Remover palavra");
        System.out.println("5. Sair");
        
    }
    
    public static void main(String[] args) throws Exception {
        // Inicializar programa
        System.out.println("Bem-vindo ao indexador de palavras!"); 
        System.out.println("Primeiro, escreva o caminho absoluto do arquivo de texto que deseja indexar:");
        System.out.println("Exemplo: C:\\Users\\SeuUsuario\\Documents\\arquivo.txt  \n(NECESSARIO ESCREVER O CAMINHO ABSOLUTO DE UM ARQUIVO TXT)");
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
    
            System.out.println("A árvore contém " + arvore.qtdNos() + " palavras indexadas.");
            
            arvore.busca(scanner);
            
            arvore.rastreioInordem();
    
            /* terceiro: mostrar para o usuário:
                *         1.quantidade de palavras armazenadas
                *         2. buscar palavra inteira
                *                -> se não existir, perguntar se quer adicionar
                *                -> mostrar tempo gasto na busca
                *         3. buscar palavra por substring
                *         4. adicionar nova palavra
                *         5. remover palavra
            */
            menu();
    
    
            
    
            System.out.println("Obrigado por usar o indexador de palavras!");

        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }
    }
}
