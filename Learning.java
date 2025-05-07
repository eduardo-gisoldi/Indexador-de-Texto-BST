
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Learning {
  public static void main(String[] args) {
    System.out.println("Hello, World!\n");


    File arquivo_teste = new File("ARQUIVO_TXT_AQUI\\arquivo.txt");
    
    String[] vetor = new String[15];
    
    try (Scanner leitor = new Scanner(arquivo_teste)) {
      
      int i = 0;
      while (leitor.hasNextLine()) {
        String conteudo = leitor.next();
        System.out.println("palavra " + (i+1) + ": " + conteudo);
        
        vetor[i] = conteudo;

        
        i++;
      }

      leitor.close();
    } catch (FileNotFoundException ex) {
      System.err.println("Arquivo não encontrado: " + ex.getMessage());
    }
    
    System.out.println(Arrays.toString(vetor));
  }
}