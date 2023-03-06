# MO 02 - Configuração e uso do Git e Github

## Chave SSH

Para realizar os push e pulls para o Github, é necessário configurar uma chave SSH no computador para a autenticação.
Siga as instruções para [Gerar nova chave SSH](https://docs.github.com/pt/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent) e [Adicionar uma nova chave SSH](https://docs.github.com/pt/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account).

## Fork

As modificações feitas não devem ser enviadas diretamente para a main no repositório principal.
Crie um fork do [projeto](https://github.com/schelip/plataforma-construcao) no github, e então, copie a chave SSH do seu repositório e realize o clone:

```bash
$ git clone <chave-ssh-fork>
```

Então, adicione o repositório principal como `upstream` no seu repositório local:

```bash
$ git remote add upstream git@github.com:schelip/plataforma-construcao.git
```

## Desenvolvimento

O primeiro passo é atualizar o branch prinicipal:

```bash
$ git checkout main
$ git pull --rebase upstream main
```

Então, crie uma branch nova para a atividade a ser desenvolvida, com o nome da tarefa no trello, partindo da main.

```bash
$ git checkout -b TXXX.X
```

Realize o commit das alterações realizadas, de preferência realizando um commit para cada sub-atividade.
Siga o modelo de mensagem abaixo:

```bash
$ git add .
$ git commit -m 'TXXX.X: message in english about the activities developed'
```

Por fim, realize o push para seu fork:

```bash
$ git push origin TXXX.X
```

## Merge Request






