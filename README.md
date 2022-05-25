# Sistemas Distruibuido

Apresentação e entrega do trabalho.

## Conteúdo

- [Pré-requisitos](#pré-requisitos)
- [Execução Aplicação](#execução)
- [Tarefa Principal](#tarefa-principal)

## Pré-requisitos

Estas são as instalações e configurações necessárias para executar o projeto.

Para executar este projeto é necessário instalar:

- Java 11

1. Após a instalação é necessário configurar as variaveis de ambiente:

   - JAVA_HOME - Apontando para o local de instalação do Java
   - PATH - Adicionar "JAVA_HOME\bin"

## Execução

1. Clonar repositório git utilizando o comando:

         git clone https://github.com/Greismorr/sistemas_distribuidos.git

2. Vá ate o terminal e inicie o RMI Registry:
        
         rmiregistry &
         
3. Adicone no java.policy a permissão:

         permission java.security.AllPermission;

## Tarefa Principal

- Reproduzir os exemplos apresentados em anexo e implementar uma versão do produtor-consumidor baseado em troca de mensagens (Sockets TCP/IP multithread) que inclua: um servidor que mantém um buffer com os itens produzidos e consumidos, e cliente(s) que solicite(m) produção/consumo de itens. Podem ser executados N clientes que se conectam a um único servidor, o qual disparará thread produtor ou thread consumidor de acordo com a solicitação.

- Observados os exemplos apresentados, implementar uma versão do produtor-consumidor baseado em Java RMI que inclua: um serviço RMI que mantém um buffer com os itens produzidos e consumidos, e cliente(s) que solicite(m) produção/consumo de itens. Podem ser executados N clientes que se conectam ao serviço, o qual disparará thread produtor ou thread consumidor de acordo com a solicitação.

### Autores
 
Feito por Elayne Natália, Gabriel Morais e Vitor Bahia.
