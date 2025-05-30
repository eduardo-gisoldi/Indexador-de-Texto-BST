====================================================================
 Indexador de Texto com Árvore Binária de Busca (BST)
====================================================================

Este sistema lê um arquivo de texto, indexa todas as palavras em uma árvore binária de busca (BST) e permite ao usuário listar, buscar, adicionar e remover palavras de forma eficiente.

--------------------------------------------------------------------
 REQUISITOS
--------------------------------------------------------------------

- Java JDK 8 ou superior instalado
- Terminal (cmd, PowerShell, ou terminal do VS Code no Windows; bash/zsh no Linux)
- Um arquivo de texto (.txt) para indexação

--------------------------------------------------------------------
 INSTALAÇÃO E EXECUÇÃO
--------------------------------------------------------------------

1. Extraia o conteúdo do arquivo ZIP em uma pasta de sua preferência.

2. Abra o terminal na pasta onde está o arquivo IndexadorBst.jar.

3. Execute o programa com o comando:

   No Windows:
       java -jar IndexadorBst.jar

   No Linux:
       java -jar IndexadorBst.jar

--------------------------------------------------------------------
 COMO USAR
--------------------------------------------------------------------

1. Ao iniciar, o programa pedirá o caminho absoluto de um arquivo .txt para indexar.
   - Exemplo no Windows: C:\Users\SeuUsuario\Documents\arquivo.txt
   - Exemplo no Linux: /home/seuusuario/Documentos/arquivo.txt
   - Digite C para cancelar.

2. O sistema irá indexar todas as palavras do arquivo, removendo acentos, caracteres especiais e padronizando para minúsculas.

3. Após a indexação, o menu será exibido:

   1. Listar palavras em ordem
      - Mostra todas as palavras indexadas em ordem alfabética, com a quantidade de ocorrências.

   2. Buscar palavra
      - Permite buscar uma palavra exata ou por substring (parte da palavra). Mostra o tempo de busca.

   3. Adicionar nova palavra
      - Permite inserir manualmente uma nova palavra na árvore.

   4. Remover palavra
      - Remove uma palavra da árvore, se existir.

   5. Sair do programa
      - Encerra o sistema.

4. Durante o uso, siga as instruções do menu e digite as opções desejadas.

--------------------------------------------------------------------
 OBSERVAÇÕES
--------------------------------------------------------------------

- Apenas palavras formadas por letras ou dígitos são consideradas válidas.
- Palavras duplicadas não são armazenadas; a quantidade é incrementada.
- Letras com acentos são convertidas para letras sem acento.
- Caracteres especiais e espaços são removidos das palavras.
- No momento, Adicionar palavras com caracteres invalidos (acentos e cedilhas) 
	diretamente pelo terminal nao é uma funcionalidade inclusa no momento pois 
	nao podemos forcar o terminal a ter o encoding UTF-8 necessario para o programa 
	normalizar as palavras corretamente.Por favor digite palavras sem acentos ou cedilhas
	e aguarde a nova versao do programa com uma interface grafica especializada 
	que sera disponibilizada na versao 2.0.

--------------------------------------------------------------------
 EXEMPLO DE USO
--------------------------------------------------------------------

Bem-vindo ao indexador de palavras!
Primeiro, escreva o caminho absoluto do arquivo de texto que deseja indexar:
Exemplo: C:\Users\SeuUsuario\Documents\arquivo.txt
(NECESSARIO ESCREVER O CAMINHO ABSOLUTO DE UM ARQUIVO TXT)
(Digitei:) C:\Users\eduds\Reps\indexador-de-texto-BST\testes\arquivoTeste.txt

Indexando palavras...

100 palavras indexadas ate o momento...
Indexacao concluida com sucesso!

--- Menu ---
A arvore contem 50 palavras unicas indexadas.

Escolha uma opcao:
1. Listar palavras em ordem, com quantidade de ocorrencias
2. Buscar palavra
3. Adicionar nova palavra
4. Remover palavra
5. Sair do programa

====================================================================