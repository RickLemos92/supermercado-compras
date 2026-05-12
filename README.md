# 🛒 Mercado App - Full Stack

> **Acesso Rápido:** [Localhost:80](http://localhost:80) (Frontend) | [API Docs](http://localhost:8080/swagger-ui.html) (Backend) | [Instruções de Instalação](#-como-executar-o-projeto)

Um sistema de mercado moderno e intuitivo, desenvolvido com uma arquitetura full-stack robusta, interface premium e recursos avançados como busca por voz.

---

## 🚀 Tecnologias Utilizadas

### **Front-end**

- **Framework:** [Vue.js 3](https://vuejs.org/) (Composition API)
- **Build Tool:** [Vite](https://vitejs.dev/)
- **Estado:** [Pinia](https://pinia.vuejs.org/)
- **Rotas:** [Vue Router](https://router.vuejs.org/)
- **HTTP Client:** [Axios](https://axios-http.com/)
- **Design:** CSS3 Moderno (Glassmorphism & Micro-animações)
- **Recursos:** Busca por voz via Web Speech API

### **Back-end**

- **Linguagem:** [Java 25](https://openjdk.org/)
- **Framework:** [Spring Boot 3.4+](https://spring.io/projects/spring-boot)
- **Persistência:** Spring Data JPA / Hibernate
- **Banco de Dados:** [MySQL 8.0](https://www.mysql.com/)
- **Build Tool:** [Gradle](https://gradle.org/)
- **Utilitários:** Lombok & Bean Validation

### **Infraestrutura**

- **Docker** & **Docker Compose**
- **Nginx** (Servidor de arquivos estáticos)

## 🏗️ Arquitetura Profissional

O projeto foi recentemente refatorado para seguir padrões de mercado de alto nível:

- **Service Layer**: Toda a lógica de negócio está isolada em serviços, desacoplando os controladores.
- **DTOs (Data Transfer Objects)**: Uso de **Java Records** para contratos de API limpos e seguros.
- **Global Exception Handling**: Tratamento de erros centralizado com respostas JSON padronizadas.
- **API Client Abstraction**: Frontend utiliza uma camada de serviços e cliente Axios centralizado.

---

## 🛠️ Como Executar o Projeto

Certifique-se de ter o **Docker** e o **Docker Compose** instalados em sua máquina.

1. Clone o repositório:

   ```bash
   git clone https://github.com/RickLemos92/supermercado-compras.git
   ```

2. Navegue até a pasta raiz do projeto:

   ```bash
   cd supermercado-compras
   ```

3. Suba os containers:

   ```bash
   docker compose up --build -d
   ```

4. Acesse as aplicações:
   - **Frontend:** [http://localhost:80](http://localhost:80)
   - **Backend API:** [http://localhost:8080/api/products](http://localhost:8080/api/products)

---

## 📂 Estrutura do Projeto

- `/frontend`: Aplicação Vue.js 3 com Composition API e Services.
- `/backend`: API Spring Boot 3.4+ com Java 25 e arquitetura em camadas (DTO, Service, Controller).
- `docker-compose.yml`: Orquestração dos serviços (App + DB MySQL).

---

Desenvolvido por [RickLemos92](https://github.com/RickLemos92) com ❤️ para facilitar a gestão de mercados.
