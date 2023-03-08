# MO 02 - Utilizando o Trello

No Trello, cada quadro representa uma sprint, e cada card a partir da segunda coluna representa uma tarefa.

## Colunas do quadro

- **Backlog do produto**: armazena todas as histórias (requisitos do sistema) no momento em que foi iniciada a sprint.
- **Backlog da sprint**: no início da sprint, armazena todas as tarefas a serem realizadas na sprint. 
Cada card está vinculado a sua história de origem, e segue o padrão de nomencladura TXXX.X, onde o valor após o ponto
representa a tarefa X e o valor antes do ponto representa a origem na história XXX.
- **Reaberto**: a tarefa já foi iniciada, mas não está tendo trabalho ativo nela e ainda não foi finalizada.
- **Em progresso**: significa que o membro já iniciou o desenvolvimento da tarefa.
- **Pronto para Revisão**: o membro já terminou o desenvolvimento, e o revisor do PR deve iniciar a revisão.
- **Revisando**: a revisão foi iniciada e está em progresso.
- **Pronto para Merge**: o desenvolvimento foi finalizado e não foram encontrados problemas,
o código pode ser mergeado à branch principal.
- **Concluído:** a tarefa foi totalmente finalizada.

## Processos a serem realizados

### Desenvolvimento

Você é responsável por todas as tarefas em que estiver definido como Membro no card.
Escolha uma tarefa a desenvolver na coluna **Backlog da Sprint** e mova o card para **Em progresso**.

Além da descrição e título do card, é possível analisar o card da história vinculada para obter informaçoes adicionais.
Sempre que necessário, defina melhor o escopo da tarefa, adicionando quais atividades que irá realizar no desenvolvimento na descrição do card.

Ao finalizar as alterações e atividades necessárias, crie um Pull Request seguindo o [MO.01](./MO.01-Git-Github.md),
adicione um comentário no modelo abaixo, adicione o Reviewer do Pull Request como Membro no card, e então mova o card para **Pronto para Revisão**.

#### Modelo de Comentário para Desenvolvimento

```markdown
# Resultado
**Desenvolvedor:** seu nome

A tarefa foi realizada com sucesso.
ou
A tarefa não pode ser realizada.

## Informações da implementação

### Atividades realizadas

* **[1]** Descricao da atividade 1
  
* **[2]** Descricao da atividade 2
    * **[2.1]** Descricao da subatividade 2.1
      
...

### Considerações

* Consideracao 1
* Consideracao 2
...
  
### Informações para o tester

Informações úteis, por exemplo, como reproduzir o bug corrigido,
em que página do sistema foi realizada a modificação,
possíveis ponto sensíveis, etc.

## Merge Request

### [server]
**Reviewer:** nome do reviewer para o PR
link para pull request para o repositório backend no github

### **[client]**
**Reviewer:** nome do reviewer para o PR
link para pull request para o repositório frontend no github

## Evidências

* **[1]** Imagem/vídeo comprovando realização da atividade 1

* **[2.1]** Imagem/vídeo comprovando realização da subatividade 2.1
```

### Revisão

Você é responsável por revisar todas as tarefas em que estiver definido como Reviewer no Github.
Escolha uma tarefa a revisar na coluna **Pronto para Revisão** e mova o card para **Revisando**.

Realize o fetch do PR seguindo o [MO.01](./MO.01-Git-Github.md). Então realize a revisão, avaliando itens como:
- Corretude da lógica implementada
- Cumprimento dos requisitos da tarefa
- Bugs encontrados
- Possíveis melhorias de performance, uso de memória, UI/UX
- Limpeza do código e bom uso de padrões de projeto

Ao finalizar a revisão, adicione um comentário seguindo o modelo abaixo e então mova o card para
**Reaberto**, caso a revisão indicou algum problema, para **Pronto para Merge**, se não foram encontrados problemas
e existe um PR a ser aceito, ou **Concluído**, se não foram econtrados problemas e não foram realizadas alteraçoes no código.

#### Modelo de Comentário para Desenvolvimento

```markdown
# Resultado
**Reviewer:** seu nome

Revisão concluída, nenhum problema encontrado.
ou
Revisão concluída, problemas foram encontrados.

## Informações da revisão

### Pontos avaliados

* [ ] **[1]** Ponto 1, com problemas

* [X] **[2]** Ponto 2, sem problemas
    * [X] **[2.1]** Subponto 2.1, sem problemas

* [ ] **[3]** Ponto 3, com problemas
    * [X] **[3.1]** Subponto 3.1, sem problemas
    * [ ] **[3.2]** Subponto 3.2, com problemas
      
...

### Considerações

* Consideracao 1
* Consideracao 2
...
  
### Informações para o desenvolvedor

Informações úteis, por exemplo, sujestões de correção, possíveis ferramentas a serem utilizadas, possíveis melhorias.

### Conclusão

O card será movido para **Reaberto**/**Pronto para Merge**/**Concluído**

## Evidências

* **[1]** Imagem/vídeo comprovando resultado do ponto 1

* **[2.1]** Imagem/vídeo comprovando resultado do subponto 2.1
```
