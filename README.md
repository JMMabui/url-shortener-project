# 🔗 Projeto de Encurtador de URLs

Este repositório contém dois subprojetos principais desenvolvidos em **Java**, utilizando arquitetura modular para criar e gerenciar URLs encurtadas de forma eficiente, além de ser projetado para integração com **AWS Lambda**, **Amazon S3** e **API Gateway**. 🚀

## 📂 Estrutura do Repositório

### 1. CreateUrlLambda
- **Descrição**: Serviço responsável por gerar URLs encurtadas.
- **Tecnologias**:
  - Java
  - Maven
  - AWS Lambda
  - Amazon S3
- **Funcionalidades**:
  - Recebe URLs longas e retorna URLs curtas únicas.
  - Armazena os dados de mapeamento em um bucket do **Amazon S3**.
  - Implementado como uma função Lambda para fácil escalabilidade.

### 2. RedirectUrlShortener
- **Descrição**: Serviço responsável por redirecionar os usuários ao URL original ao acessar o link encurtado.
- **Tecnologias**:
  - Java
  - Maven
  - AWS Lambda
  - Amazon S3
  - API Gateway
- **Funcionalidades**:
  - Consulta o mapeamento de URLs encurtadas no **Amazon S3**.
  - Redireciona automaticamente o usuário para o destino correto.
  - Integrado com **API Gateway** para criar endpoints HTTP seguros.

---

## 🚀 Como Executar

### Pré-requisitos
- **Java** 8+ instalado.
- **Maven** configurado no ambiente.
- Conta na **AWS** com permissões para usar Lambda, S3 e API Gateway.

### Passos Locais
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/url-shortener-project.git
   cd url-shortener-project

