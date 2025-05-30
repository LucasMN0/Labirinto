# Projeto: Sistema de Labirinto com Aventureiros

Projeto desenvolvido para a disciplina de **Progamação Orientada à Objetos** no curso de **Ciência da Computação** da **Universidade Católica de Pernambuco**, ministrada pelo professor Lucas Rodolfo Celestino de Farias.

## Equipe
- Bento Guilherme Gomes Oliveira -RA:00000852680
- Arthur Amaral de Souza -RA:00000852603
- Lucas Mendes Nóbrega -RA:00000852727

## Objetivos

-Desenvolver um jogo de labirinto usando Java, onde jogadores devem navegar por um labirinto.
  
-Permitir que o Aventureiro colete tesouros escondidos e evite perigos, registrando o progresso.

-Implementar as principais entidades: Aventureiro, Tesouro, Perigo e Labirinto, cada uma com seus respectivos atributos e comportamentos.

-Utilizar coleções (como ArrayList) para gerenciar a estrutura do labirinto, lista de tesouros, perigos e itens coletados.

-Garantir uma experiência estável e segura com tratamento de exceções, evitando erros comuns de movimentação ou coleta.

-Aplicar conceitos de polimorfismo através de heranças na classe Tesouro, criando efeitos variados para os tesouros.

-Garantir abstração e encapsulamento, organizando o código com modificadores de acesso e métodos adequados.

---

## Funcionalidades

### Aventureiros
- Cadastro e configuração de personagens com base em diferentes **classes**.
- Cada classe possui atributos únicos e acesso a **kits específicos**.
- Possuem métodos de ataque, defesa e uso de itens.

### Sistema de Itens
- **ItemComum** e **ItemEquipável**, permitindo que aventureiros carreguem e equipem itens.
- Itens afetam atributos como vida, defesa, dano, entre outros.

### Armadilhas (`Armadilha.java`)
- Elementos do labirinto que causam dano ou efeitos nos aventureiros ao serem ativadas.
- As armadilhas possuem tipos variados com danos específicos.

### Centro (`Centro.java`)
- Centro de operações que gerencia aventureiros e ações de sistema.
- Estrutura para adição de menus e interações com o usuário.

###  Loja
- Sistema onde aventureiros podem comprar e vender itens com moedas coletadas no labirinto.
- Integração com inventário e verificação de saldo.

### Monstruário
- Catálogo de inimigos e armadilhas encontradas e derrotados pelos aventureiros.
- Registro de história e descrição dos monstros.

### Perigos
- Eventos aleatórios e perigos ambientais que afetam o progresso no labirinto.
- Podem incluir armadilhas ocultas, inimigos entre outros.

### Sistema de Combate
- Mecânica de batalha por turnos entre aventureiro e inimigo.
- Sistema de iniciativa e efeitos de status.

### Musica
- Jogo tem uma música própria.

---

## Estrutura

- Código dividido em múltiplas classes, promovendo organização e manutenção.
- Facilmente expansível com novas classes, inimigos ou itens.
