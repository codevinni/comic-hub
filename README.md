# 📖 ComicHub

**ComicHub** é uma plataforma de gerenciamento de quadrinhos desenvolvida para facilitar a organização, catalogação e empréstimo de revistas em quadrinhos. O sistema permite que administradores cadastrem caixas, quadrinhos e gerenciem usuários, enquanto leitores podem consultar o acervo disponível, realizar empréstimos e acompanhar suas devoluções através de notificações por e-mail.

## Sobre

Este projeto foi desenvolvido como parte de um trabalho acadêmico com o objetivo de demonstrar a implementação de uma aplicação web completa utilizando o ecossistema Jakarta EE.

O foco principal é a arquitetura baseada em **JPA (Java Persistence API)**, explorando a integração entre front-end e back-end através de **JSF (Jakarta Server Faces)** com **PrimeFaces**. As responsabilidades no projeto foram separadas em camadas (Model-DAO-Bean), injeção de dependências, validação de dados, autenticação de usuários e sistema de notificações automatizadas.

## Funcionalidades

- **Gerenciamento de Quadrinhos**: Cadastro de quadrinhos organizados por caixas
- **Sistema de Empréstimos**: Controle de empréstimos com data prevista de devolução
- **Controle de Usuários**: Cadastro e autenticação de usuários com diferentes níveis de acesso
- **Verificação por E-mail**: Sistema de verificação de conta através de código enviado por e-mail
- **Notificações Automáticas**: Lembretes por e-mail para empréstimos em atraso
- **Gerenciamento de Caixas**: Organização dos quadrinhos em caixas identificadas por cor
- **Painel Administrativo**: Interface exclusiva para administradores gerenciarem todo o sistema

## Tecnologias Usadas

O projeto utiliza as seguintes tecnologias e bibliotecas:

- **Java 22**: Linguagem base do projeto
- **Jakarta EE 10**: Plataforma empresarial para desenvolvimento de aplicações web
- **JSF (Jakarta Server Faces) 4.0**: Framework para construção de interfaces web baseadas em componentes
- **PrimeFaces 15**: Biblioteca de componentes UI ricos para JSF
- **Weld 4.0**: Implementação de referência do CDI (Contexts and Dependency Injection)
- **Hibernate 6**: Framework ORM (Object-Relational Mapping) para persistência de dados
- **Hibernate Validator 8**: Implementação da Bean Validation API
- **PostgreSQL**: Sistema gerenciador de banco de dados relacional
- **Spring Security Crypto**: Para criptografia de senhas
- **Mailtrap**: Serviço de envio de e-mails
- **Maven**: Gerenciamento de dependências e build

## Arquitetura do Projeto

```
ComicHub/
├── src/main/java/br/tsi/comichub/
│   ├── bean/          # Managed Beans (Controllers)
│   ├── dao/           # Data Access Objects
│   ├── model/         # Entidades JPA
│   ├── enums/        
│   ├── filter/        # Filtros de autorização
│   ├── service/       # Serviços (AutoReminder)
│   └── utils/         
└── src/main/webapp/
    ├── templates/     # Templates JSF
    └── *.xhtml        # Páginas XHTML
```

## Setup

### Requisitos

- PostgreSQL instalado e em execução
- Java 22 ou superior
- Servidor de aplicação compatível com Jakarta EE 10 (ex: Apache Tomcat 10.1+)

### Configuração do Banco de Dados

#### Usando o Backup SQL (Recomendado)

Para facilitar a configuração, um script SQL completo está disponível em `database/backup.sql`.

**Passos:**

1. Crie um banco de dados vazio no PostgreSQL:
   ```sql
   CREATE DATABASE comichub;
   ```

2. Execute o script de backup:
   ```bash
   psql -U postgres -d comichub -f database/backup.sql
   ```

   Ou importe via ferramenta gráfica (pgAdmin, DBeaver, etc.).

3. Configure as credenciais do banco de dados em `src/main/java/br/tsi/comichub/dao/JPAUtil.java` e `src/main/java/META-INF/persistence.xml`:
   - URL de conexão (ex: jdbc:postgresql://localhost:5432/comichub)
   - Nome do banco de dados
   - Usuário
   - Senha

### Configuração de E-mail

Para utilizar o sistema de notificações por e-mail, configure as credenciais do Mailtrap (se cadastre no site oficial) em `src/main/java/br/tsi/comichub/utils/Mail.java`.

### Como Executar

Há duas formas de executar este projeto:

#### Opção 1: Via Maven + Servidor de Aplicação (Recomendado para Produção)

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/comichub.git
   cd comichub
   ```

2. **Configure o banco de dados** conforme descrito acima

3. **Compile o projeto**:
   ```bash
   mvn clean install
   ```

4. **Implante o arquivo WAR** gerado em `target/comichub.war` no seu servidor de aplicação:
   - Apache Tomcat 10.1+
   - WildFly
   - GlassFish

5. **Acesse a aplicação** através do navegador:
   ```
   http://localhost:8080/comichub
   ```

#### Opção 2: Via Eclipse EE + Tomcat 10 (Desenvolvimento)

Esta foi a configuração utilizada durante o desenvolvimento do projeto:

**Pré-requisitos:**
- Eclipse IDE for Enterprise Java and Web Developers
- Apache Tomcat 10.1+ instalado
- PostgreSQL rodando localmente

**Passos:**

1. **Importar o projeto**:
   - File → Import → Existing Maven Projects
   - Selecione a pasta do projeto
   - Aguarde o Eclipse baixar as dependências Maven

2. **Configurar o Tomcat no Eclipse**:
   - Window → Preferences → Server → Runtime Environments
   - Add → Apache Tomcat v10.1
   - Aponte para o diretório de instalação do Tomcat
   - Apply and Close

3. **Adicionar projeto ao servidor**:
   - Aba "Servers" (inferior) → Botão direito no Tomcat → Add and Remove
   - Mova o projeto de "Available" para "Configured"
   - Finish

4. **Executar**:
   - Botão direito no projeto → Run As → Run on Server
   - Selecione o Tomcat 10.1 configurado
   - Finish
   - O navegador abrirá automaticamente em `http://localhost:8080/comichub`

## Credenciais de Acesso

Após executar o script SQL de backup, você poderá acessar com as seguintes credenciais:

**Usuário Administrador:**
- **E-mail**: admin@comichub.com
- **Senha**: admin

**Usuário Regular:**
- **E-mail**: vini@email.com
- **Senha**: admin

## Observações Importantes

- **Senhas**: As senhas dos usuários são armazenadas utilizando BCrypt através do Spring Security Crypto
- **Verificação**: Usuários precisam verificar o e-mail antes de acessar o sistema (os usuários do backup já estão verificados)
- **Empréstimos**: O sistema envia lembretes automáticos para empréstimos próximos ao vencimento
- **Disponibilidade**: Quadrinhos emprestados ficam marcados como indisponíveis até a devolução