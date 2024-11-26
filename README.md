# URL SHORTENER SYSTEM

O projecto trata-se de um sistema de encurtamento de URLs utilizando a AWS como infraestrutura serveless.
O objectivo e permitir que os usuarios criem URLs curtas que redirecionem para URLs originais, com um tempo de expiracao cinfiguravel.
O sistema e composto por duas funcoes Lambda: 
* A primeira funcao e responsavel por gerar e armazenar os links encurtados em um bucket S3, junto com informacoes como a URL original e o tempo de expiracao;
* A segunda funcao gerencia o redireionamento verificando o codigo da URL curta e validando se a URL ainda esta dentro do prazo de expiracao antes de redirecionar o usuaruio.

## Início

Instruções sobre como configurar o ambiente de desenvolvimento para executar o projeto.

### Pré-requisitos

- JDK +
- Maven
- ...

### Instalação

Passos para instalar o projeto.

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/nome-do-projeto.git

# Navegue até o diretório do projeto
cd nome-do-projeto

# Compile o projeto
mvn clean package
