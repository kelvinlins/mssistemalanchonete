# mssistemalanchonete

  Bem-vindo ao projeto **mssistemalanchonete**!  
  Esta aplicação é o backend de um sistema de controle de pedidos e auto-atendimento para uma lanchonete. Nela é possível cadastrar clientes, produtos e pedidos bem como gerenciar seu ciclo de vida.  
  Ela foi contruída usando arquitetura hexagonal, java 17 e spring-boot 3.

## Executando via Kubernetes (Minikube)
  Estando na branch `develop` entre no diretorio `./k8s` e execute o script `./apply-manifests.sh`, nele se encontram todos os manifestos criados para o deploy da app.   
  Após a execução do script e os manifestos criados, execute o comando `minikube service <service_name>` utilizando o nome do seu service criado. 
  
  Utilize a `URL` retornada para o consumo da aplicação - *Exemplo abaixo*. 
  
  ![Exemplo](https://github.com/kelvinlins/mssistemalanchonete/blob/c9f6bb1244d54604314e147ce19024ba77c671f7/assets/exemplo-minikube-service.png)

## Executando através do Docker Compose:
  Garanta que você está na branch `develop`.  
  Olhe para a pasta `./docker-compose` você encontrará tudo que é necessário para subir a aplicação utilizando docker compose (um yml e as enviroments referenciadas nele).  
  Basta, a partir de `./docker-compose`, no seu linux, executar :  
  ```
  docker compose up
  ```

## Contrato
  Com aplicação rodando, acesse o endpoint `/sistema-lanchonete/api/v1/swagger-ui/index.html` e você terá o detalhamento dos endpoints expostos na aplicação.

## A aplicação
  Conforme escrito anteriormente, podemos gerenciar clientes, produtos e pedidos.

### Clientes
  Os clientes (pessoas que compram da lanchonete) podem ter cpf, e-mail e nome.  
  Um cliente pode se cadastar utilizando apenas o cpf ou utilizando nome e e-mail.  
  O cadastro de um cliente possui um código que é utilizado para referencia-lo. 

### Produtos
  Os produtos possuem código, nome, descrição, preço e categoria (lanche, acompanhamento, bebida e sobremesa).

### Pedidos
  Os pedidos são compostos por combos, que são uma coleção de produtos e quantidades a eles associadas. Caso o cliente se identifique ao fazer o pedido, o pedido também contém a referencia ao cliente.  
  Existem endpoints específicos para adicionar e remover produtos e adicionar e remover combos(vazios ou com produtos relacionados).  
  Após a escolha de produtos ser finalizada, é necessário fazer o checkout para que o pedido seja encaminhado para a cozinha.
  Após o checkout é possível acompanhar o status do pedido e o tempo de espera.

### Desenho de Arquitetura dos Requisitos do Negócio:

- Interface de Seleção de Produtos
  - Identificação do Cliente: Opções para identificação via CPF, cadastro com nome e e-mail, ou anonimato.
  - Montagem de Combo: Clientes podem montar combos de Lanche, Acompanhamento, Bebida.
  - Exibição de Produtos: Nome, descrição e preço de cada produto exibidos em cada etapa.
  
- Pagamento
  - Integração com Mercado Pago utilizando QRCode.

- Acompanhamento de Pedido
  - Monitor para clientes acompanharem o progresso do pedido com etapas: Recebido, Em preparação, Pronto, Finalizado.

- Entrega
  - Notificação ao cliente quando o pedido estiver pronto para retirada.
  - Atualização do status do pedido para "Finalizado" quando retirado pelo cliente.

- Acesso Administrativo
  - Gerenciamento de Clientes: Coletar dados dos clientes identificados para campanhas promocionais.
  - Gerenciamento de Produtos e Categorias: Gerenciar produtos com categorias fixas: Lanche, Acompanhamento, Bebida, Sobremesa.
  - Acompanhamento de Pedidos: Monitorar pedidos em andamento e tempo de espera.

### Componentes Principais:
  - Backend: Microsserviços para gerenciar autenticação, produtos, pedidos, pagamentos e notificações.
  - Banco de Dados: Armazenamento de dados de clientes, produtos, pedidos e histórico de transações.
  - Integração de Pagamentos: Serviço de integração com Mercado Pago.
  - Serviço de Notificações: Para notificar clientes sobre o status do pedido.

### Serviços e Ferramentas AWS:
  - Amazon EC2: Para hospedar os microsserviços.
  - Amazon RDS: Banco de dados relacional.
  - Network Load Balancer: Distribuição de tráfego para instâncias EC2.
  - Docker: Containerização dos microsserviços.

### Descrição dos Componentes

  - Usuário: Interface do usuário para fazer pedidos, visualizar produtos, e acompanhar o status do pedido.
  - Network Load Balancer: Distribui o tráfego de rede para as instâncias EC2, garantindo alta disponibilidade e balanceamento de carga.
  - Amazon EC2 com Docker: Hospeda os microsserviços containerizados utilizando Docker
  - Serviço de autenticação: Gerencia a autenticação e identificação dos clientes.
  - Serviço de pedido: Gerencia os pedidos dos clientes, atualiza o status do pedido e coordena com outros serviços.
  - Interface de pedido: Gerencia os produtos disponíveis para os clientes, incluindo nome, descrição, preço e categoria.
  - Serviço de Pagamento: Integração com Mercado Pago para processar pagamentos via QRCode.
  - Serviço de Notificação: Envia notificações aos clientes sobre o status do pedido 
  - Amazon RDS: Banco de dados relacional para armazenar dados de clientes, produtos e pedidos.

### Desenho de Arquitetura da Infraestrutura: 
![Diagrama - Arquitetura](/assets/infra.png)

### Banco de Dados: 
![MER - Banco de dados](https://github.com/kelvinlins/mssistemalanchonete/blob/7708e866687a1babab1b8f074414e292b4ac2a81/assets/MER.png)

Para a aplicação, foi utilizado como banco de dados uma instância do banco relacional **PostgreSQL** através do serviço **Amazon RDS**. A escolha desse serviço ocorreu principalmente pela praticidade na configuração e gerenciamento do banco de dados por parte da AWS, e por suas capacidades de alta disponibilidade e escalonamento, que são muito importantes em ambiente produtivo. A escolha do PostgreSQL como SGBD se deu por conta de sua característica estruturada (SQL), visto que os bancos de dados relacionais garantem consistência e integridade dos dados (ACID), que são características importantes nesse tipo de aplicação, já que estamos trabalhando com cadastro de clientes, login e efetuação de pedidos.

### Autenticação: 
![Diagrama - Autenticação](https://github.com/kelvinlins/mssistemalanchonete/blob/a365165909f2cf20882c7c1b87fc8bc1a9e99ba5/assets/auth.jpg)

O usuário pode realizar o login utilizando o endpoint `/sistema-lanchonete/api/v1/auth` informando seu **CPF** e **senha** utilizados na realização do seu cadastro, caso o usuário tenha cadastro e suas informações estiverem corretas, será retornado um token JWT válido por 1 hora que deverá ser passado nas chamadas dos endpoints da aplicação. O API Gateway será responsável por executar a função lambda que realiza a validação do token JWT enviado nas requisições da aplicação, caso o mesmo seja válido, a requisição poderá ser feita com sucesso, caso contrario a aplicação retornará um erro de autenticação.

### Video explicativo - Fase 2
Segue o [video](https://www.youtube.com/watch?v=aRSbvq5WTiY) explicativo da fase 2 detalhando o funcionamento da aplicação na arquitetura escolhida pelo grupo. 

## CI/CD
Alguns passos de ci/cd foram implementados utilizando terraform e github-actions.   
O cluster EKS e a infra necessária para ele são disponibilizadas utilizando [esse respositório](https://github.com/guilherme0541/mslanchonete-infra-eks).   
O instaância RDS é provisionada através [desse](https://github.com/guilherme0541/mslanchonete-db-secreteKubernetes).   
Já a lambda utilizada para autenticação é provisionada [aqui](https://github.com/Guimaj/lambda-auth-mslanchonete).   
Toda essa infra precisa ser preparada previamente para que a automação desse repositório possa ser executada.   
As actions desse repositório compilam, buildam e publicam a aplicação em um repositório privado no ECR e fazem deploy no cluster EKS. Elas também disponibilizam a api através de um load balancer e um apigateway com autorização feita pela lambda citada acima.

### Variaveis e Secrets
Para executar os scripts diretamente do github, é necessário criar a variable `AWS_REGION` que é o código da região AWS e  as secrets `TSECRET, AWS_ACCESS_KEY_ID e AWS_SECRET_ACCESS_KEY`, respectivamente a secret usada no JWT, o ID e chave de acesso de um usuário AWS com permissões suficientes para criar e alterar os recursos citados acima.  

### Execução
A automação ( **Deploy terraform** ) roda a partir de pull-requests para a `main`: na abertura ela publica a nova versão da aplicação e valida as alterações necessárias no ambiente, no merge ela aplica as alterações. Também é possivel acionar a automação manualmente no menu action do github.
Para fazer o desprovisionamento da infra também existe uma action nesse repositório: **Deploy terraform**. Ela precisa ser acionada manualmente e escolhendo "Yes_sure" mo menu suspenso o processo é iniciado.

### Consumindo a API
Após a conclusão da action de deploy você precisará pegar o endereço do host no api gateway na sua conta AWS.
