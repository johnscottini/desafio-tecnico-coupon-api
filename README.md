# Coupon API

Uma API REST para gerenciamento de cupons.

Obs: No repositório está o arquivo da collection do postman para executar as requisições.

## Visão Geral

### Clean Architecture

A aplicação segue os princípios de Clean Architecture, separando as responsabilidades em camadas bem definidas:

- **Domain**: Possui a lógica de negócio, sem depender de frameworks. A entidade `Coupon` encapsula as validações e regras de negócio.
- **Application**: Implementa os casos de uso (`UseCase`) que orquestram operações entre a camada de domínio e a infraestrutura.
- **Infrastructure**: Responsável pela integração com frameworks como Spring Web e JPA.

Essa separação garante que a lógica de negócio **não tenha dependência de frameworks**, facilitando testes e manutenção e seguindo os princípios da Clean Arch.

### Use Cases

A aplicação implementa o padrão de casos de uso:
- `CreateCouponUseCase`: Criar novo cupom com validações
- `DeleteCouponUseCase`: Deletar cupom (soft-delete)
- `GetAllCouponsUseCase`: Listar cupons ativos

Cada UseCase é injetado no controller, permitindo testabilidade e desacoplamento.

### Testes

- **Builders, Scenarios e Test**: Utiliza arquivos de Scenarios com builders para encapsular a lógica dos testes. A ideia é deixar os dados necessários para o teste no scenario para que o test fique mais enxuto.

Foram criados testes de integração e unitários, com arquivo `application-tst.properties` com configurações próprias do ambiente de teste.

### Exceções Personalizadas

Todas as exceções de negócio herdam de `BusinessException`, permitindo tratamento centralizado:
- `CouponAlreadyExistsException`
- `CouponNotFoundException`
- `InvalidCouponCodeException`
- `InvalidCouponDiscountValueException`
- `InvalidCouponExpirationDateException`
- `CouponAlreadyDeletedException`

O `ExceptionAdvice` trata essas exceções globalmente, retornando respostas HTTP padronizadas.

### Swagger/OpenAPI

Documentação automática com **Springdoc OpenAPI**:
- Todos os endpoints documentados com `@Operation` e `@ApiResponses`
- Schemas automáticos para request/response DTOs
- Acessível em: `/swagger-ui.html`

### 🔧 Ambientes

Configuração por profile:
- **dev**
- **tst**

Cada ambiente tem suas próprias properties, facilitando a separação das configurações.

## Stack Tecnológico

- **Java 17**
- **Spring Boot 3.5.14**
- **Spring Data JPA**
- **Spring Validation**
- **Springdoc OpenAPI 2.8.16** (Swagger)
- **Lombok**
- **H2 Database**
- **JUnit 5 + Mockito** (testes)
- **Docker**

## Executar

### Localmente

```bash
# Development
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"

# Testes
mvn test
```

### Docker

A aplicação possui container Docker, segue instruções de execução:

```bash
# Build e execução
docker-compose up --build
```

A API estará disponível em `http://localhost:8080` com o profile `dev` ativado por padrão.

## API Endpoints

```
GET    /api/coupons          - Listar cupons ativos
POST   /api/coupons          - Criar cupom
DELETE /api/coupons/{code}   - Deletar cupom
```

---
