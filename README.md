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

## Explicação das Decisões Técnicas

1. **RestAssured**: Biblioteca madura e amplamente utilizada para testes de API em Java, com suporte a validações poderosas e sintaxe fluente.

2. **JUnit 5**: Versão mais recente do JUnit, com suporte a lambdas e extensões, ideal para testes modernos.

3. **Lombok**: Reduz boilerplate code com anotações como @Getter e @Setter.

4. **ExtentReports**: Gera relatórios HTML detalhados e profissionais automaticamente.

5. **GitHub Actions**: Configuração simples de CI/CD sem necessidade de ferramentas externas.

6. **Estrutura de Pacotes**: Organização clara por responsabilidade (models, services, tests, etc.).

7. **Testes**: Cobertura dos cenários da collection Postman, incluindo fluxos positivos e negativos.

## Como Executar

Para executar o framework, basta clonar o repositório, configurar o Java 11 e executar `mvn test`. Os relatórios serão gerados automaticamente na pasta `target/reports/`.

### Pré-requisitos
- JDK 21 instalado
- Maven instalado

### Executando os testes
```bash
mvn clean test
