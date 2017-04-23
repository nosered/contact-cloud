# contact-cloud
Trabalho desenvolvido para disciplina de Computação em Nuvem - UFC - Quixadá

#### Para executar o projeto você precisa:

###### 1. criar o arquivo src/main/resources/application.properties, exemplo:

    # Credenciais (IAM User) da AmazonWS
    cloud.aws.credentials.accessKey = abCdEFgHiJKLMnOpqRsTUvWxYz
    cloud.aws.credentials.secretKey = abCdEFgHiJKLMnOpqRsTUvWxYz
    cloud.aws.region.static = ex-region-1
    
    # Para banco de dados na máquina local
    spring.datasource.driverClassName = org.postgresql.Driver
    spring.datasource.url = jdbc:postgresql://localhost:5432/contactcloud_db
    spring.datasource.username = postgres
    spring.datasource.password = postgres
    
    # Para usar o serviço RDS da amazonWS
    cloud.aws.rds.database-instance
    cloud.aws.rds.database-instance.username = nome_de_usuario
    cloud.aws.rds.database-instance.password = senha_do_usuario
    cloud.aws.rds.database-instance.readReplicaSupport = false
    cloud.aws.rds.database-instance.databaseName = nome_banco_de_dados
    # IMPORTANTE: "database-instance" deve ser substituido pelo nome da instância criada no AmazonWS RDS;
    
    spring.jpa.generate-ddl = true
    spring.jpa.show-sql = true
    
###### 2. Criar um banco de dados com o nome "contactcloud_db" (AmazonWS RDS ou local).

###### 3. Criar um Bucket no AWS S3 com nome "contactcloudstore".

###### 4. Executar o projeto:

    mvn spring-boot:run
