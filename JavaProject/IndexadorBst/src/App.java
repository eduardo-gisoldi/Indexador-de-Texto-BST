public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        // Testes

        // Teste de No
        No no = new No("teste");
        no.setEsq(new No("esquerda"));
        no.setDir(new No("direita"));
        no.setQtd(3);
        no.showNo();        
        System.out.println("\n\n\n");

        // Teste de ArvoreBST
        ArvoreBST arvore = new ArvoreBST();
        arvore.insere("teste");
        arvore.insere("teste2");
        arvore.insere("teste3");
        arvore.insere("teste2");
        arvore.insere("abelha");
        arvore.insere("mel"); 
        arvore.insere("melancia");
        arvore.insere("bolacha");
        arvore.insere("abelha");
        arvore.insere("abelha"); 

        // Testes de rastreios
        System.out.println("\n\n");
        arvore.rastreioInordem();
        System.out.println("\n\n");
        arvore.rastreioPosordem();
        System.out.println("\n\n");
        arvore.rastreioPreordem();
        System.out.println("\n\n");

        // Teste de busca
        if (arvore.busca("abelha") != null) {
            System.out.println("Palavra encontrada: " + arvore.busca("abelha").getPalavra());
        } else {
            System.out.println("Palavra não encontrada");
        }

        // Outros testes
        System.out.println("Maior: " + arvore.maior().getPalavra());
        System.out.println("Menor: " + arvore.menor().getPalavra());
        System.out.println("Raiz: " + arvore.getRaiz().getPalavra());
        System.out.println("Quantidade de nós: " + arvore.qtdNos());



        /* 
         * primeiro: pedir path do arquivo de texto
         * 
         * segundo: armazenar as palavras em uma BST
         * 
         * terceiro: mostrar para o usuário:
         *         1.quantidade de palavras armazenadas
         *         2. buscar palavra inteira
         *                -> se não existir, perguntar se quer adicionar
         *                -> mostrar tempo gasto na busca
         *         3. buscar palavra por substring
         *         4. adicionar nova palavra
         *         5. remover palavra
         * 
         * 
         */

    }
}
