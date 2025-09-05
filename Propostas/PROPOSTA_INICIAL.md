PROJETO DE PROGRAMAÇÃO


Indexador de Texto Utilizando Árvores Binárias de Pesquisa

O processamento de texto é uma das mais importantes aplicações de sistemas computacionais na atualidade. Uma enorme variedade de problemas envolve o tratamento correto e eficiente de texto, sendo que sistemas de armazenamento e recuperação de texto representam a base para tratar importantes problemas, tais como buscadores web, sistemas de compartilhamento de documentos, bancos de dados cadastrais, redes sociais, etc. Outro fator relevante é a capacidade de trabalhar de forma eficiente com grandes volumes de dados, visando tornar o sistema escalável ao longo do tempo.
Neste projeto, iremos construir um indexador de texto utilizando Árvores Binárias de Pesquisa (BST). Árvores binárias de pesquisa são estruturas de dados que permitem armazenar e recuperar dados de forma eficiente, usando estratégias de organização de dados de forma hierárquica. A BST é formada por um conjunto conectado de elementos (que chamamos de nós). Cada nó de uma BST possui referências para outros dois nós, um à esquerda e outro à direita.
A propriedade fundamental de uma BST é que, para todo nó X da árvore, os seus nós à esquerda possuem valores menores que X. De forma semelhante, os seus nós à direita possuem valores maiores que X.
O objetivo deste trabalho é construir um sistema de indexação de texto que realiza o armazenamento e a recuperação de dados usando árvores binárias de pesquisa.

GRUPOS
Este projeto deverá ser desenvolvido por grupos de estudantes formados por dois ou três alunos da disciplina. 

ESPECIFICAÇÕES BÁSICAS
- Seu sistema deverá ser implementado usando a linguagem de programação Java.
- Seu sistema deverá apresentar, no mínimo, as seguintes operações:
- Armazenar todas as palavras de um arquivo-texto em um banco de dados (árvore binária de pesquisa).
- Buscar uma palavra em seu banco de dados.
- Buscar palavras a partir de uma substring.
- Calcular o tempo gasto na recuperação de uma palavra.
- Calcular a quantidade de palavras armazenadas.
- Adicionar novas palavras ao banco de dados.
- Remover palavras do banco de dados.

OBSERVAÇÕES
As palavras, ao entrarem no banco de dados, devem ser padronizadas com todas as letras minúsculas.
Serão consideradas palavras válidas somente aquelas formadas por letras ou dígitos. Sendo assim, seu sistema não deve armazenar caracteres especiais (‘#’, ‘$’ , ’&’, etc).
Todas as palavras também não devem conter espaços brancos, tabulações e quebras de linha.
Letras com acentos devem ser substituídas por letras sem acentos.
Seu sistema não deve trabalhar com palavras duplicadas. Desse modo, antes de inserir uma palavra no banco de dados, você deve garantir que essa mesma palavra não esteja presente no banco. 
Você poderá utilizar as implementações sugeridas da classe Java vista em aula para seu trabalho.

O QUE DEVO ENTREGAR?

Cada grupo deve entregar os seguintes documentos:
- Documentação básica do sistema, explicando como realizar a instalação e como utilizar as funcionalidades do sistema.
- O código-fonte do sistema em arquivo compactado no formato ZIP
- Video de demonstração do sistema
- DATA DE ENTREGA DOS DOCUMENTOS: 30 de maio de 2025.
- A entrega dos documentos será pelo Google Classroom, na área da disciplina.