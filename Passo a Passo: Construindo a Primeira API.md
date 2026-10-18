# Passo a Passo: Construindo a Primeira API com Spring Boot

Este documento detalha o passo a passo prático, explicando não apenas o "como fazer", mas também os conceitos teóricos e fundamentos por trás de cada decisão de design e arquitetura no ecossistema Java.

---

## 1. Fundamentos Teóricos: Ecossistema Spring e Java
* **O que é o Spring?** É um ecossistema abrangente para o desenvolvimento em Java, muito utilizado no mercado corporativo devido à sua robustez, segurança e padronização.
* **O papel do Spring Boot:** Atua como um facilitador (*opiniated framework*) que elimina a necessidade de configurações complexas e XMLs verbosos, permitindo colocar aplicações no ar de forma ágil através de stater dependencies e servidores embutidos.
* **O conceito de Verbosidade (*Verbosity*):** O Java é frequentemente debatido por sua verbosidade (exigir mais linhas de código em comparação a linguagens dinâmicas como Python ou Node.js). Contudo, essa característica garante forte tipagem, escalabilidade, controle refinado e previsibilidade, tornando-o o padrão ouro para aplicações corporativas críticas (sistemas financeiros, e-commerce de alta escala, companhias aéreas).

---

## 2. Geração do Projeto via `Spring Initializr`
O primeiro passo prático consiste em configurar os metadados e as dependências básicas do projeto através da ferramenta oficial web `start.spring.io`:

1. **Ferramenta de Build:** Selecionou-se o **Maven**, o gerenciador de pacotes tradicional e consolidado no ecossistema Java corporativo, responsável por gerenciar bibliotecas e o ciclo de vida do projeto através do arquivo `pom.xml`.
2. **Metadados (Padrão *Reverse Domain*):** 
   * *Group:* `com.exemplo` (ou o domínio invertido da organização).
   * *Artifact:* `minhaprimeiraapi` (nome do projeto/artefato).
   * *Packaging:* `Jar` (empacotamento padrão executável).
3. **Versão do Java:** Alinhamento da versão do JDK instalada na máquina de desenvolvimento com a versão suportada pelo Spring Boot escolhida.
4. **Adicionando Dependências Essenciais:**
   * **Spring Web:** Fundamental para o desenvolvimento de aplicações web orientadas a arquitetura REST, trazendo nativamente o ecossistema de controle de rotas HTTP e serialização JSON.
   * **Spring Data MongoDB:** Adicionada para preparar a aplicação para a futura persistência de dados utilizando um banco NoSQL orientado a documentos.

---

## 3. Configuração do Ambiente na IDE (`IntelliJ IDEA`)
Após descompactar o arquivo `.zip` gerado pelo Initializr, o projeto foi importado e configurado no **IntelliJ IDEA**:

1. **Abertura Correta:** Certificou-se de abrir a pasta raiz do projeto evitando aninhamentos desnecessários de diretórios.
2. **Ajustes de SDK e Compilador:** 
   * Acessou-se *Project Structure* para garantir que o JDK correto estivesse associado ao projeto.
   * Configurou-se o nível da linguagem (*Language Level*) compatível com a versão do Java utilizada.
3. **Sincronização do Maven:** Caso ocorram avisos de dependências não resolvidas ou erros de classpath na classe principal, executou-se a sincronização e instalação das dependências via painel lateral do Maven (`Reload All Maven Projects` / `Install`).

---

## 4. O Mecanismo do Servidor Web Embutido (`Tomcat`)
* Diferente de abordagens legadas onde era necessário instalar e configurar um servidor de aplicação externo (como o Tomcat ou JBoss) separadamente, o Spring Boot encapsula o **Apache Tomcat** diretamente dentro do arquivo executável JAR da aplicação.
* Ao executar a classe principal (contendo a anotação `@SpringBootApplication`), a aplicação inicializa de forma autônoma, subindo o servidor web embutido por padrão na porta `8080`.

---

## 5. Desenvolvimento Prático: Criando o Primeiro Controller e Rotas HTTP
Para validar o funcionamento da API, foi estruturado o primeiro controlador web:

1. **Organização de Pacotes:** Criação de um pacote dedicado a controladores (`controllers`) seguindo a convenção de arquitetura de pacotes do Java.
2. **Criação da Classe Controladora (`ControllerEstudante`):**
   * **`@RestController`**: Anotação crucial que informa ao Spring que esta classe é um componente web controlador e que suas respostas devem ser convertidas diretamente para o corpo da resposta HTTP (geralmente JSON).
   * **`@RequestMapping("/api/estudantes")`**: Define a rota base ou prefixo de URL para todos os endpoints contidos nessa classe.
3. **Mapeamento de Verbos HTTP (`GET`):**
   * Utilização de **`@GetMapping`** para mapear requisições do tipo leitura/busca.
   * Exemplo prático de retorno de uma mensagem de boas-vindas textual ou objeto simulado.
   * Demonstração complementar da extração de parâmetros diretamente da URL utilizando **`@PathVariable`**.
4. **Testes de Integração Manual:**
   * Utilização da ferramenta **Insomnia** para disparar requisições HTTP do tipo `GET` em direção ao endereço local (`http://localhost:8080/api/estudantes`), comprovando que a API recebe, processa e responde corretamente às chamadas externas.

---

## 💻 Código Fonte Completo

```java
package com.example.minha_primeira_api.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudantes")
public class Estudante {

    @GetMapping
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/id")
    public String helloParam(@RequestParam int id) {
        return "Hello, world! " + id;
    }

    // Ajustado para aceitar o ID na URL: /api/estudantes/123
    @PostMapping("/{id}")
    public String asd(@PathVariable int id) {
        return "POST - Hello, world! " + id;
    }

    // Ajustado para aceitar o ID na URL: /api/estudantes/123
    @PutMapping("/{id}")
    public String dsdsd(@PathVariable int id) {
        return "PUT - Hello, world! " + id;
    }

    // Ajustado para aceitar o ID na URL: /api/estudantes/123
    @DeleteMapping("/{id}")
    public String dsdd(@PathVariable int id) {
        return "DELETE - Hello, world! " + id;
    }
}
```

---

##  Detalhamento das Rotas e URLs de Exemplo

### 1. Listagem Geral (`GET`)
* **Anotação:** `@GetMapping`
* **Comportamento:** Responde a uma requisição GET na rota base da classe, retornando uma mensagem padrão.
* **URL de Exemplo:** 
  ```text
  GET http://localhost:8080/api/estudantes
  ```

### 2. Busca por Parâmetro Query String (`GET`)
* **Anotação:** `@GetMapping("/id")` combinada com `@RequestParam`
* **Comportamento:** Captura um parâmetro enviado via query string (parâmetro de consulta na URL).
* **URL de Exemplo:** 
  ```text
  GET http://localhost:8080/api/estudantes/id?id=10
  ```

### 3. Criação com ID na URL (`POST`)
* **Anotação:** `@PostMapping("/{id}")` combinada com `@PathVariable`
* **Comportamento:** Mapeia o verbo POST esperando um identificador diretamente no caminho (path) da URL.
* **URL de Exemplo:** 
  ```text
  POST http://localhost:8080/api/estudantes/123
  ```

### 4. Atualização com ID na URL (`PUT`)
* **Anotação:** `@PutMapping("/{id}")` combinada com `@PathVariable`
* **Comportamento:** Mapeia o verbo PUT para atualizar registros com base no identificador da URL.
* **URL de Exemplo:** 
  ```text
  PUT http://localhost:8080/api/estudantes/123
  ```

### 5. Exclusão com ID na URL (`DELETE`)
* **Anotação:** `@DeleteMapping("/{id}")` combinada com `@PathVariable`
* **Comportamento:** Mapeia o verbo DELETE para remover um recurso utilizando o ID informado no caminho.
* **URL de Exemplo:** 
  ```text
  DELETE http://localhost:8080/api/estudantes/123
  ```
