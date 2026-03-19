# Gerador de QR Code com Spring Boot e AWS S3 (LocalStack)

Uma API RESTful desenvolvida em Java com Spring Boot que gera imagens de QR Code a partir de textos/URLs e faz o upload automático para um bucket S3. O projeto utiliza o **LocalStack** e o **Docker Compose** para emular o ambiente da AWS localmente, facilitando o desenvolvimento e os testes sem gerar custos na nuvem.

## 🚀 Tecnologias Utilizadas

* **Java 25**
* **Spring Boot 4.0.3**
* **AWS SDK for Java** (Integração com S3)
* **ZXing** (Biblioteca para geração do QR Code)
* **Docker & Docker Compose**
* **LocalStack** (Emulação da AWS S3)

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas na sua máquina:

* [Java JDK 25+](https://jdk.java.net/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/) (Docker Desktop rodando)
* Uma IDE da sua escolha (IntelliJ IDEA, Eclipse, VS Code)
* Postman ou Insomnia para testar as requisições.

## 🛠️ Como executar o projeto

### 1. Subindo a infraestrutura (LocalStack S3)

O projeto conta com um arquivo `docker-compose.yml` que automatiza a criação do servidor do LocalStack e já cria o bucket S3 necessário (`qrcode-bucket`) automaticamente para você.

Abra o terminal na raiz do projeto e execute:

```bash
docker-compose up -d
```

> **Nota:** O contêiner de setup criará o bucket automaticamente em segundo plano. Você pode prosseguir para o próximo passo. Para derrubar a infraestrutura após o uso, utilize `docker-compose down`.

### 2. Rodando a Aplicação Spring Boot

Com a infraestrutura de pé, inicie a aplicação Java. Você pode fazer isso diretamente pelo botão de "Play" da sua IDE na classe `QrcodeApplication.java`, ou via terminal executando:

```bash
mvn spring-boot:run
```
A aplicação iniciará na porta padrão `8080`.

## 📌 Como testar a API

### Gerar e fazer upload de um QR Code

Faça uma requisição **POST** para o endpoint da aplicação (ajuste o corpo da requisição conforme os atributos da sua classe de DTO/Request).

* **URL:** `http://localhost:8080/qrcode/send`
* **Método:** `POST`
* **Content-Type:** `application/json`

**Exemplo de Corpo da Requisição (JSON):**
```json
{
  "text": "[https://meu-portfolio.com](https://meu-portfolio.com)",
  "bucket": "qrcode-bucket"
}
```

**Exemplo de Resposta (Sucesso):**
A API retornará o link direto para você acessar o arquivo gerado dentro do seu LocalStack:

```text
Copiar e colar no navegador: http://localhost:4566/qrcode-bucket/nome-do-arquivo.png
```

Basta copiar a URL fornecida e colar no seu navegador para visualizar ou baixar o QR Code gerado!
