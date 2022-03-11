A JPA define um metamodelo que pode ser usado em tempo de execução para obter informações sobre o
mapeamento ORM feito.

Esse metamodelo inclui a lista das propriedades mapeadasde uma entidade, seus tipos e cardinalidades.

O metamodelo pode ser usado com Criteria API para substituir as string que referenciam proproedades.

O uso de metamodelo evita erros em tempo de execução, pois qualquer alteração no código-fonte de 
mapeamento altera também o metamodelo.

Podemos gerar o metamodelo automaticamente.Para isso vamos usar a dependencia hiberate-jpamodelgen.

<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-jpamodelgen -->
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-jpamodelgen</artifactId>
    <version>5.4.28.Final</version>
    <scope>provided</scope>
</dependency>

Uma vez adicionada no pom.xml, basta executar o projeto normalmente com o intellij ou rodar o comando package
do Maven.
Assim que aparecer no console a mensagem BUILDE SUCCESS, já pode conferir no pacote de dominio as classes de metamodel
já estão criadas.

Agora podemos referenciar as propriedades das entidades através das classs geradas.Por exemplo, podemos usar as classes 
Vehiculo_ e Owner_.


