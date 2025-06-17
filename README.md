# API Automation Framework

Framework de automação de testes para a API dummyjson.com, implementado em Java com RestAssured e JUnit 5.

## Tecnologias Utilizadas

- Java 11
- RestAssured
- JUnit 5
- Lombok
- ExtentReports
- Maven
- GitHub Actions

### Explicação das Decisões Técnicas

1. **RestAssured**: Biblioteca madura e amplamente utilizada para testes de API em Java, com suporte a validações poderosas e sintaxe fluente.
2. **JUnit 5**: Versão mais recente do JUnit, com suporte a lambdas e extensões, ideal para testes modernos.
3. **Lombok**: Reduz boilerplate code com anotações como @Getter e @Setter.
4. **ExtentReports**: Gera relatórios HTML detalhados e profissionais automaticamente.
5. **GitHub Actions**: Configuração simples de CI/CD sem necessidade de ferramentas externas.
6. **Estrutura de Pacotes**: Organização clara por responsabilidade (models, services, tests, etc.).
7. **Testes**: Cobertura dos cenários da collection Postman, incluindo fluxos positivos e negativos.

## Como Executar

Para executar o framework, basta clonar o repositório, configurar o Java 11 e executar `mvn test`.
Os relatórios serão gerados automaticamente na pasta `target/reports/`.

### Pré-Requisitos

1. **Java JDK 21 ou superior**: [Baixar e instalar Java](https://www.oracle.com/br/java/technologies/downloads/)
2. **Maven**: [Baixar e instalar Maven](https://maven.apache.org/install.html)

### Clocanado o repositório

```
 git clone https://gitlab.com/iuri.ramos.reducino-group/DesafioSCD.git
 cd DesafioSCD
```

### Executando os testes

```bash
mvn clean test
```

### Executando os testes no Gitlab

Normalmente, os testes são executados automaticamente sempre há alguma alteração no código, todavia, caso queira executar os testes manualmente, basta clicar em `Run Pipeline` do menu `Pipeline`

### Relatório dos testes

Quando os testes são executados localmente, é gerado pasta `target/reports/` no qual o relatório é salvo em extensão `.html`, porém, caso o testes tenha sido executado pela `Pipeline`, o relatório de teste fica disponivel para download no `job` de `test`.

## Bugs e Apontamentos de Melhoria

### Alguns bugs foram observados durante a execução dos testes:

* Campo `domain` que consta na documentação no response do [GET /users](https://dummyjson.com/users), não retorna na aplicação.
* Os campos `users.address.stateCode`, `users.address.country`, `users.company.stateCode`, `users.company.country`, `users.crypto.coin`, `users.crypto.wallet`, `users.crypto.network`, `users.crypto.role`, `total`, `skip` e `limit` foram vieram no response da [GET /users](https://dummyjson.com/users), porém, tais campos não constam na relação de campos listados na documentação.
* Embora na documentão não seja mensionado, é possivel buscar usuário por id com o seguinte cURL:
  `curl --location 'https://dummyjson.com/users/:id'`
* Outro ponto que não é mensionado na documentação, é possivel adicionar parametros na [GET /users](https://dummyjson.com/users), como `skip` para listar mais páginas de usuários e `limit` para aumentar a quantidade de usuários por página.
* Na documentação, a requisição do [POST /auth/login](https://dummyjson.com/auth/login), consta o nome do campo como `token`, todavia, seu retorno foi como `accessToken`.
* A requisição de [GET /auth/products](https://dummyjson.com/auth/products), conforme a documentção, solicita `Content-Type: application/json`, no entanto, requisições de `GET` não necessitam de `Content-Type`, já que esse cabeçalho serve para indicar o que será enviado no body de requisições como `POST`, `PATCH`, `DELETE`, etc.
* Para a [GET /auth/products](https://dummyjson.com/auth/products), os campos `tags`, `sku`,`weight`, `dimensions.width`, `dimensions.height`, `dimensions.depth`,`warrantyInformation`, `shippingInformation`, `availabilityStatus`, `reviews.rating`, `reviews.comment`, `reviews.date`, `reviews.reviewerName`, `reviews.reviewerEmail`, `returnPolicy`, `minimumOrderQuantity`, `meta.createdAt`, `meta.updatedAt`, `meta.barcode` e `meta.qrCode` veiram no response da requisição, porém não constam na documentação.
* Ainda para a [GET /auth/products](https://dummyjson.com/auth/products), não foi possivel realizar requisição que retornasse `status code` `403`, e para o `status code` `401`, era esperado que o retorno fosse `{ "name": "JsonWebTokenError", "message": "Invalid/Expired Token!" }`, porém retornou `{ "message": "Invalid/Expired Token!" }`.
* Na requisição [POST /products/add](https://dummyjson.com/products/add0), não há nenhum tipo de validação de campos obrigatórios no `body`, com isso, a requisição retorna `status code` `201` mesmo que não seja preenchido o `title` ou o `price`, que são informações importantes para o cadastro de produto.
* Quando é feito o cadastro de produto pela [POST /products/add](https://dummyjson.com/products/add0), o campo `id` sempre retorna o mesmo valor `195`.
* Para [GET /products/{id}](https://dummyjson.com/products/2), foi possivel alterar o metodo `GET` para `DELETE`, o trouxe retorno confirmando a exclusão do produto com o id informado.

### Apontamentos de Melhoria a serem observados:

* Descrever na documentação todos os `status code` que cada endpoint retorna.
* Garantir que os restornos descritos na documentação refletem na API.
