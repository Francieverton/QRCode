# 📱 Microserviço Gerador de QR Code (Spring Boot + AWS S3)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 🎯 Finalidade do Projeto
Este projeto é uma API REST desenvolvida em Java com Spring Boot que atua como um microserviço de geração de QR Codes. 
O sistema recebe uma URL via requisição HTTP, converte esse link em uma imagem de QR Code (utilizando a biblioteca Google ZXing) e realiza o upload automático dessa imagem para um bucket no **Amazon S3**, retornando o link público gerado para o usuário.

A arquitetura foi desenhada com foco em **Clean Architecture (Ports and Adapters)**, garantindo que a regra de negócio seja totalmente isolada da infraestrutura de nuvem.

> **⚠️ Observação Importante sobre a Nuvem:** > Atualmente, o projeto está configurado para rodar localmente utilizando o **LocalStack** (um emulador de serviços da AWS). Futuramente, a aplicação será conectada à nuvem real da AWS. Graças ao design desacoplado utilizando a interface `StoragePort`, a migração para produção exigirá apenas a exclusão da classe de configuração local, sem a necessidade de alterar uma única linha da regra de negócio.

---

## 🛠️ Tecnologias Utilizadas
* **Java 17+**
* **Spring Boot 3**
* **Google ZXing** (Para processamento e geração da matriz do QR Code)
* **AWS SDK for Java v2** (Integração com o S3)
* **Docker & LocalStack** (Emulação da infraestrutura AWS localmente)
* **AWS CLI** (Para criação do bucket local)

---

## ⚙️ Pré-requisitos
Antes de rodar o projeto, você vai precisar ter instalado em sua máquina:
* [JDK 17 ou superior](https://adoptium.net/)
* [Maven](https://maven.apache.org/)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (Iniciado e rodando)
* [AWS CLI](https://aws.amazon.com/pt/cli/)

---

## 🚀 Passo a Passo: Como rodar a aplicação

### 1. Subindo a infraestrutura local (LocalStack)
Abra o seu terminal e inicie o contêiner do LocalStack na porta 4566 usando o Docker:
```bash
docker run --rm -d -p 4566:4566 -p 4510-4559:4510-4559 localstack/localstack
```

### 2. Criando o Bucket S3 localmente
Com o LocalStack rodando, utilize o AWS CLI para criar o bucket falso chamado `qrcode-bucket`:
```bash
aws --endpoint-url=http://localhost:4566 s3 mb s3://qrcode-bucket
```

### 3. Rodando o Spring Boot
Faça o clone deste repositório, abra o projeto na sua IDE de preferência (IntelliJ, Eclipse, VS Code) e execute a classe principal `QrcodeApplication.java`. A API estará disponível na porta `8080`.

---

## 🧪 Testando a API
Você pode utilizar ferramentas como **Postman** ou **Insomnia** para testar a rota.

**Endpoint:** `POST http://localhost:8080/qrcode/send`

**Corpo da Requisição (JSON):**
```json
{
  "url": "[https://www.google.com.br](https://www.google.com.br)"
}
```

**Exemplo de Resposta (Status 200 OK):**
```json
{
  "url": "[https://qrcode-bucket.s3.amazonaws.com/5c161cba-ac30-46ea-9d01-e99616ccd45c.png](https://qrcode-bucket.s3.amazonaws.com/5c161cba-ac30-46ea-9d01-e99616ccd45c.png)"
}
```
*(Lembrando que, como estamos usando o LocalStack, para visualizar a imagem no navegador você deve acessar: `http://localhost:4566/qrcode-bucket/nome-do-arquivo.png`)*

---

## 👨‍💻 Autor
Desenvolvido por **Francieverton Oliveira**
