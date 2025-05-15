import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Learning {
  public static void main(String[] args) {
    File arquivo_teste = new File("ARQUIVO_TXT_AQUI\\arquivo.txt");

    //String[] vetor = new String[30];

      try {
        Scanner leitor = new Scanner(arquivo_teste);

        String palavra1 = leitor.next();
        String palavra2 = leitor.next();

        if (palavra1.compareTo(palavra2) > 0) {
          System.out.println("A palavra " + palavra1 + " eh maior que " + palavra2);
        } else if (palavra1.compareTo(palavra2) < 0) {
          System.out.println("A palavra " + palavra1 + " eh menor que " + palavra2);
        } else {
          System.out.println("As palavras sao iguais");
        }

      } catch (FileNotFoundException ex) {
        System.err.println("Arquivo não encontrado: " + ex.getMessage());
      }

    //System.out.println(Arrays.toString(vetor));
  }
}

/*
try (Scanner leitor = new Scanner(arquivo_teste)) {

  /**
   * Loop passando por cada palavra do arquivo
  /
  int i = 0;
  while (leitor.hasNextLine()) {
    String conteudo = leitor.next();
    System.out.print("palavra " + (i) + ": " + conteudo);

    vetor[i] = conteudo;

    if (i > 0) {
      System.out.println(" --- eh maior que o anterior? -> " + (vetor[i].compareTo(vetor[i - 1])));
    }

    if (i > 0) {
      System.out.println(" --- eh maior que o anterior? -> " + (vetor[i].compareTo(vetor[i - 1]) > 0));
    } else {
      System.out.println(" --- eh maior que o anterior? -> " + false);
    }
    i++;
  }

  leitor.close();
} catch (FileNotFoundException ex) {
  System.err.println("Arquivo não encontrado: " + ex.getMessage());
}
*/