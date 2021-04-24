# FitnessStack

Código do Spring gerado no Spring Initializer do vs code. 
Para correr basta abrir isto no vs code, instalar as extensões que ele recomenda e alterar este ficheiro: /src/main/resources/application.properties

```yml
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/NOME_BD_QUE_QUISEREM?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=PASSWORD_MYSQL
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Para colocar o servidor a correr, basta correr a main em /src/main/java/ft/backend/BackendApplication.java