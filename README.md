# mssistemalanchonete


Bem-vindo ao projeto **mssistemalanchonete**!  
Esta aplicação é o backend de um sistema de controle de pedidos e auto-atendimento para uma lanchonete. Nela é possível cadastrar clientes, produtos e pedidos bem como gerenciar seu ciclo de vida.  
Ela foi contruída usando arquitetura hexagonal, java 17 e spring-boot 3.

## Pré-Requisitos
Para rodar essa aplicação você precisa de um banco de dados postgres, de java 17 e do maven.  
Entretanto, recomendamos fortemente que você utilize o docker e o docker compose para facilitar esse processo (nós criamos um docker-compose.yml :wink: ). 
Caso ainda não os tenha instalado, visite a [documentação oficial do Docker](https://docs.docker.com/get-docker/) para obter instruções de instalação.

## Executando através do Docker Compose:
Garanta que você está na branch `develop`.  
Olhe para a pasta `./docker-compose` você encontrará tudo que é necessário para subir a aplicação utilizando docker compose (um yml e as enviroments referenciadas nele).  
Basta, a partir de `./docker-compose`, no seu linux, executar :  
```
docker compose up
```

## Contrato
Com aplicação rodando, acesse `http://localhost:8080/sistema-lanchonete/api/v1/swagger-ui/index.html` e você terá o detalhamento dos endpoints expostos na aplicação.

## A aplicação
Conforme escrito anteriormente, podemos gerenciar clientes, produtos e pedidos.

### Clientes
Os clientes(pessoas que compram da lanchonete) podem ter cpf, e-mail e nome.  
Um cliente pode se cadastar utilizando apenas o cpf ou utilizando nome e e-mail.  
O cadastro de um cliente possui um código que é utilizado para referencia-lo. 

### Produtos
Os produtos possuem código, nome, descrição, preço e categoria (lanche, acompanhamento, bebida e sobremesa).

### Pedidos
Os pedidos são compostos por combos, que são uma coleção de produtos e quantidades a eles associadas. Caso o cliente se identifique ao fazer o pedido, o pedido também contém a referencia ao cliente.  
Existem endpoints específicos para adicionar e remover produtos e adicionar e remover combos(vazios ou com produtos relacionados).  
Após a escolha de produtos ser finalizada, é necessário fazer o checkout para que o pedido seja encaminhado para a cozinha.
Após o checkout é possível acompanhar o status do pedido e o tempo de espera.