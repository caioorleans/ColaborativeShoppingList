# Lista de Compras Colaborativa - API

Este projeto é uma API desenvolvida com Spring que tem como objetivo permitir a criação e o gerenciamento colaborativo de listas de compras. Com ela, um usuário pode criar listas, convidar outros usuários para participar e adicionar itens às listas de forma compartilhada.

## Funcionalidades Implementadas

* **Criação de usuário e autenticação**
* **Criação de lista de compras**
* **Edição de lista de compras**
* **Exclusão de lista de compras**
* **Criação de itens em uma lista de compras**
* **Exclusão de item de uma lista de compras**
* **Marcar item como concluído**

## Funcionalidades Pendentes

* Recuperação de senha
* Envio de e-mails
* Convite para participar de uma lista de compras
* Edição de itens da lista de compras

## Tecnologias e Dependências Utilizadas

* **Spring Web**: para criação da API REST
* **Spring Security**: para autenticação e autorização
* **Spring Data JPA**: para persistência de dados
* **Lombok**: para redução de boilerplate
* **BCrypt**: para hash de senhas
* **ModelMapper**: para conversão entre DTOs e entidades
* **Swagger**: para documentação interativa da API

## Como Executar o Projeto

Este projeto está conteinerizado com Docker. Para executar localmente:

1. **Clone o repositório:**

   ```bash
   git clone <URL-do-repositório>
   cd <nome-do-projeto>
   ```

2. **Construa e suba os containers com Docker Compose:**

   ```bash
   docker-compose up --build
   ```

3. **Acesse a API:**
   A API estará disponível em `http://localhost:8080`

4. **Documentação da API (Swagger):**
   Acesse `http://localhost:8080/swagger-ui.html` para visualizar e testar os endpoints disponíveis.

---

Este documento será atualizado conforme novas funcionalidades forem implementadas ou modificações importantes ocorrerem no projeto.
