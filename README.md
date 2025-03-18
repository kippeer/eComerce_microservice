# Microsserviço de E-commerce

Um microsserviço de e-commerce robusto construído com Spring Boot, apresentando recursos de autenticação de usuários, gerenciamento de produtos e processamento de pedidos.

## Funcionalidades

- Gerenciamento de Usuários
  - Registro e Autenticação
  - Segurança baseada em JWT
  - Controle de acesso baseado em funções
- Gerenciamento de Produtos
  - Operações CRUD para produtos
  - Gerenciamento de estoque
  - Tratamento de preços
- Processamento de Pedidos
  - Criação e gerenciamento de pedidos
  - Acompanhamento do status do pedido
  - Suporte a múltiplos status (PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE, CANCELADO)

## Stack de Tecnologia

- Java 11
- Spring Boot 2.7.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Redis (para cache)
- Swagger/OpenAPI (documentação da API)
- JUnit 5 & Mockito (testes)
- Docker & Docker Compose

## Pré-requisitos

- Java 11 ou superior
- Maven 3.6+
- Docker e Docker Compose
- PostgreSQL 13+
- Redis 6+

## Como Começar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/ecommerce-microservice.git
   cd ecommerce-microservice
   ```

2. Inicie os serviços necessários usando Docker Compose:
   ```bash
   cd docker
   docker-compose up -d
   ```

3. Compile a aplicação:
   ```bash
   mvn clean install
   ```

4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`

## Documentação da API

Depois que a aplicação estiver em execução, você pode acessar a interface do Swagger em:
```
http://localhost:8080/swagger-ui/
```

## Endpoints da API

### Usuários
- POST `/api/users/register` - Registrar novo usuário
- POST `/api/users/login` - Autenticar usuário e obter token JWT
- GET `/api/users/profile` - Obter perfil do usuário atual

### Produtos
- GET `/api/products` - Listar todos os produtos
- GET `/api/products/{id}` - Obter produto por ID
- POST `/api/products` - Criar novo produto
- PUT `/api/products/{id}` - Atualizar produto
- DELETE `/api/products/{id}` - Excluir produto

### Pedidos
- GET `/api/orders` - Listar todos os pedidos
- GET `/api/orders/{id}` - Obter pedido por ID
- POST `/api/orders` - Criar novo pedido
- PUT `/api/orders/{id}/status` - Atualizar status do pedido

## Configuração do Banco de Dados

A aplicação usa PostgreSQL como banco de dados principal. A configuração pode ser encontrada em `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Testes

O projeto inclui testes unitários abrangentes para todas as camadas de serviço. Para executar os testes:

```bash
mvn test
```

## Suporte Docker

A aplicação pode ser containerizada usando o Dockerfile fornecido. Para construir e executar a imagem Docker:

```bash
docker build -t ecommerce-microservice .
docker run -p 8080:8080 ecommerce-microservice
```

## Como Contribuir

1. Faça um fork do repositório
2. Crie sua branch de feature (`git checkout -b feature/RecursoIncrivel`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona algum RecursoIncrivel'`)
4. Faça push para a branch (`git push origin feature/RecursoIncrivel`)
5. Abra um Pull Request

