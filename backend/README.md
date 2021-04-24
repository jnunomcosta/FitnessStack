# FitnessStack

Para a BD:
Correr na pasta onde está o ficheiro docker-compose.yml
```
docker-compose up -d 

docker exec -it mysql bash

mysql -u root -p 
(inserir palavra passe root -> fitstack_secretpw)

GRANT ALL PRIVILEGES ON *.* TO 'fitness_stack'@'%';

FLUSH PRIVILEGES;
```

Código do Spring gerado no Spring Initializer do vs code. 
Para correr basta abrir isto no vs code, instalar as extensões que ele recomenda e verificar os dados da bd neste ficheiro: /src/main/resources/application.properties

Para colocar o servidor a correr, basta correr a main em /src/main/java/ft/backend/BackendApplication.java