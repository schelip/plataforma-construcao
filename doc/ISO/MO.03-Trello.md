# MO 02 - Utilizando o Trello

Cada card na primeira coluna representa uma história, e cada card a partir da segunda coluna representa uma tarefa.

## Colunas do quadro

- **Backlog do produto**: armazena todas as histórias (requisitos do sistema) no momento em que foi iniciada a sprint.
- **Backlog da sprint**: no início da sprint, armazena todas as tarefas a serem realizadas na sprint. 
Cada card está vinculado a sua história de origem, e segue o padrão de nomencladura TXXX.X, onde o valor após o ponto
representa a tarefa X e o valor antes do ponto representa a origem na história XXX.
- **Re-Aberto**: a tarefa já foi iniciada, mas não está tendo trabalho ativo nela e ainda não foi finalizada.
- **Em progresso**: significa que o membro já iniciou o desenvolvimento da tarefa.
- **Pronto para Revisão**: o membro já terminou o desenvolvimento, e o revisor do PR deve iniciar a revisão.
- **Revisando**: a revisão foi iniciada e está em progresso.
- **Pronto para Merge**: o desenvolvimento foi finalizado e não foram encontrados problemas,
o código pode ser mergeado à branch principal.
- **Concluído - SXXX:** a tarefa foi totalmente finalizada.

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

Ao finalizar a revisão, adicione comentários no github para cada erro encontrado, e então seleciona a opção de requisitar
mudanças ou de aprovar. Por fim, mova o card para **Reaberto**, caso a revisão indicou algum problema, para
**Pronto para Merge**, se não foram encontrados problemas e existe um PR a ser aceito, ou **Concluído**, se não foram
econtrados problemas e não foram realizadas alteraçoes no código.

## Tags

Existe um conjunto de tags que são utilizadas para apresentar informações adicionais para cada card.

- *SXXX: dd/mm/yy - dd/mm/yy* indica qual a sprint de desenvolvimento do card. Ou seja, ao ser finalizado, o card deve
ser movido para a coluna de concluído da sprint correspondente a essa tag.
- *Merged sem revisão* indica que, por algum motivo, um card que realiza alterações no código teve as alterações aceitas
sem passar revisão.
- *Prioridade* quando o card deve ser realizado antes de qualquer outro, por ser urgente ou essencial para outras tarefas.
- *Cancelado* quando o card era planejado, mas não será realizado por algum motivo, nem nessa sprint e nem em nenhum outra.
