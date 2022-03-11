Caching é um termo usado quando armazenamos objetos em memória para acesso mais rápido no futuro.

O cache de segundo nível(L2) é compartilhado entre todos os EntityManagers da aplicação.

Para ativar o cache de segundo nível, precisamos selecionar uma implementação de cache e adicionar
algumas configurações no arquivo persistence.xml


<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-jcache</artifactId>
	<version>5.4.10.Final</version>
</dependency>
<dependency>
	<groupId>org.ehcache</groupId>
	<artifactId>ehcache</artifactId>
	<version>3.8.1</version>
</dependency>


Definimos a implementação do cache no elemento hibernate.cache.region.factory_class do arquivo 
persistence.xml.Em nosso exemplo, especificamos uma classe que não será a provedora do cache em si.
Ela irá procurar por algum provedor que implemente a especificação de cache do Java chamada JCache.

Assim como temos a especificação JPA que é implementada pelo Hibernate, temos também a especificação
JCache que é implementada pelo EhCache.

Através do elemento hibernate.javax.cache.provider nós deixamos explícita a classe do EhCache que contém
a implementação.

Por fim, utilizando o elemento hibernate.javax.cache.uri, podemos informar o caminho para o arquivo de configuração
do EhCache.O conteúdo dele pode ficar como o conteudo da classe ehcache.xml.

Com a configuração, está sendo dito que será permitido armazenar até 1000 objetos na memória e cada objeto tem um tempo
de vida no cache de 20 segundos.

O elemento shared-cache-mode no persistence.xml aceita alguns valores que configuram o cache de segundo nível.

- ENABLE_SELECTIVE(padrão): as entidades não são armazenadas no cahce, a menos que sejam explicitamente anotadas 
com @Cacheable(true).

- DISABLE_SELECTIVE: as entidades são armazenadas no cache, a menos que sejam explicitamente anotadas com 
@Cacheable(false).

- ALL: todas entidades são sempre armazenadas no cache.

- NONE : nenhuma entidade é armazenada no cache(desabilita o cache de segundo nível). 
 




