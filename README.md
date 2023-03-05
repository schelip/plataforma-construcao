# Plataforma Construção

Academia online para jogadores de esportes eletrônicos desenvolvida para a disciplina de Construção de Software.

## Rodando o Projeto

### Dependências

O projeto utiliza o maven, então o primeiro passo é realizar o [download](https://maven.apache.org/download.cgi?Preferred=ftp%3A%2F%2Fmirror.reverse.net%2Fpub%2Fapache%2F) ou configurar a IDE para utilizar um wrapper.

Então, é necessário realizar o download da JDK a ser utilizada, no nível 17. A build recomendada é a [Liberica](https://bell-sw.com/pages/downloads/).

Para utilizar a JDK com o maven, configure a variável de sistema `JAVA_HOME`, o que pode ser feito temporariamente no windows (utilizando [Git Bash](https://git-scm.com/downloads)) com o comando:

```bash
$ set JAVA_HOME="C:\caminho\para\jdk"
```

Em seguida, é necessário configurar o banco de dados e adicionar os dados para a conexão.

### Execução

Utilizando uma instalação standalone do maven:
```bash
$ mvn clean spring-boot:run
```

Utilizando o wrapper:
```bash
$ ./mvn clean spring-boot:run
```

Ou adicione uma run configuration para a `main` da classe `PlataformaConstrucaoApplication`:
![](./doc/assets/images/main-configuration.png)

O endpoint será executado na porta 8080.