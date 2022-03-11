**conteúdo disponivel ebook - algaworks- JPA- Guia Definitivo

Gerenciando estados

3.1. Estados e ciclo de vida

Objetos de entidade são instâncias de classes mapeadas usando JPA, que ficam na memória e representam registros
do banco de dados.Essas instâncias possuem um ciclo de vida, que é gerenciado pelo JPA.

EntityManager é um serviço responsável por gerenciar as entidades, através dele é possível gerenciar o ciclo
de vida das entidades, operação de sincronização com a base de dados.
 O EntityManager pode ser gerenciado de duas formas:Gerenciado pelo Container, Gerenciado pela Aplicação.

Os estados do ciclo de vida das entidades são: transient(ou new), managed, detached e removed.

As transições entre os estados são feitas através de métodos do EntityManager.

Transient: são instanciados usando o operador new.Não afeta os registros do banco.

Managed: objetos gerenciados, são instancias de entidade que possuem um identificador e representam um registro
da tabela do banco de dados.Ex. um objeto persistido através de um método do EntityManager,  como o persist.
Objetos gerenciados estão sempre associados a um contexto de persistência, ou seja, qualquer alteração 
nesses objetos são sincronizadas com o banco de dados.

Removed: uma instância de uma entidade pode ser excluída através do método remove do EntityManager.
Um objeto entra no estado removed quando ele é marcado para ser eliminado, mas é fisicamente excluído durante a
sincronização com o banco de dados.

Detached:Um objeto sempre inicia no estado 'transient' e depois pode se tornar gerenciado. Quando o EntityManager pe fechado,
continua existindo uma instância do objeto, mas já no estado detached.Esse estado existe para quando os objetos estão 
desconectados, não tendo mais sincronia com o banco de dados.A JPA fornece operações para reconectar esses objetos a um 
novo EntityManager.


1.Contexto de persistencia
É uma coleção de objetos gerenciados por um EntityManager.Se uma entidade é pesquisada, mas ela já existe no contexto de
persistencia, o objeto é retornado sem acessar o banco de dados.Esse recurso é chamado de cache de primeiro nível.
Uma mesma entidade pode ser representada por diferentes objetos na memória, desde que seja em diferentes instâncias 
de EntityManagers. Em uma única instancia de EntityManager, apenas um objeto que representa determinada entidade(com o
mesmo identificador) pode ser gerenciada.pág 47

2.Objetos desanexados
São objetos em um estado que não é gerenciado pelo EntityManager,mas ainda representam entidades no banco.
As alterações em objetos desanexados não são sincronizadas com o banco de dados.Utilizamos objetos desanexados, por exemplo,
quando eles são expostos para alteração através de páginas web e apenas em um segundo momento o usuário solicita a 
gravação das alterações do objeto.

*Regras para implementação de chaves primárias compostas
- É uma classe POJO.
- É uma classe pública com um construtor público sem argumento.
- Se você usar o acesso baseado em propriedade, as propriedades da classe de chave primária serão públicas ou protegidas.
- É serializável.
- Ele define os métodos equals e hashCode. A semântica de igualdade de valor para esses métodos deve ser consistente com a
igualdade do banco de dados para os tipos de banco de dados para os quais a chave é mapeada.
- Uma instância do EmbeddedId é usada com a operação EntityManager find (), para encontrar uma entidade por seu id.

3.Objetos embutidos
Objetos embutidos são componenetes de uma entidade, cujas propriedades são mapeadas para a mesma tabela da entidade.
Em algumas situação, podemos precisar usar a orientação a objetos para componentizar nossas entidades, mas manter os
dados  em uma única tabela.Outra situação comum é o mapeamento de tabelas de banco de dados legados, onde não é permitido
alterar a estrutura das tabelas.

4.Mapeamento entidades

Mapeamento um-para-um
@OneToOne

Veiculo 1 ---> 1 proprietario
 
implementação unidirecional:
*obtem-se o proprietário a partir de um veículo
Na classe veículo adicione o atributo proprietario, mapeando com OneToOne
adicione também a anotação @JoinColumn(name = "cod_proprietario")


implementação bidirecional:
*obtem-se o proprietário a partir de um veículo e o veículo a partir de um propritário
Na classe veículo adicione o atributo proprietario, mapeando com @OneToOne
adicione também a anotação @JoinColumn(name = "cod_proprietario")

Na classe proprietario  adicione o atributo veiculo, mapeando com @OneToOne
mappedBy "proprietario".
ex:   @OneToOne(mappedBy = "proprietario")
	  private Veiculo veiculo;

5.Mapeamento muitos-para-um
conhecido como @Many-to-one
*um veículo poderá possuir apenas um proprietário, mas um proprietário
poderá estar associado a muitos veículos.
A anotação @ManyToOne indica a multiplicidade do relacionamento entre
veículo e proprietário

Veiculo * ---> 1 proprietario

Na classe Veiculo adicionar 
    @ManyToOne
    @JoinColumn(name = "proprietario_codigo")
    private Proprietario proprietario;
 Caso queira se fazer a associação bidirecional mapear um atributo na 
 entidade owner com - @OneToMany.
 
    
 Mapeamento um-para-muitos
 conhecido como @OneToMany
 deve ser utilizada para mapear coleções

Veiculo * ---> 1 proprietario

na classe proprietario adicionar
	@OneToMany(mappedBy = "proprietario")
	private List<Veiculo> veiculos;


Mapemento muitos-para-muitos
conhecido como many-to-many

Veiculo* ----- * Acessorio

*relacionamentos muito-para-muitos necessitam de tabela associativa
o jpa possui um recursso de schema generation que poderá recriar as tabelas automaticamente.
*um mapeamento com @ManyToMany cria tabela de associação com os nomes das entidades relacionadas,
separados por underscore, com as colunas com nomes também gerados automaticamente.
*o anotação @JoinTable podemos customizar o nome da tabela de associação e das colunas




Herança
Utilize este recurso com moderação, muitas vezes é melhor mapear usando associações do que
herança.
A JPA define 4 formas de se fazer o mapeamento de herança:
 - Tabela única para todas as classes(single table)
 - Uma tabela para cada classe da hierarquia(joined)
 - Uma tabela para cada classe concreta(table per class)
 - Superclass
 
 *Tabela única para todas as classes(single table)
 primeira estratégia é a melhor em termos de performance e simplicidade, porém seu maior problema 
 é que as colunas das propriedades declaradas nas classes filhas precisam aceitar valores nulos.
 A falta da constraint NOT NULL pode ser um problema sério no ponto de vista de integridade de 
 dados.
 
 *Uma tabela para cada classe da hierarquia(Joined)
 
 
 
 
 *Uma tabela para cada classe concreta(Table per class)
 

 *Superclass


Modos de acesso
O estado das entidades precisam ser acessíveis em tempo de execução pelo provedor JPA, para
poder ler e alterar os valores e sincronizar com o banco de dados.
Existem duas formas de acesso ao estado de uma entidade: field access e property access.

Os métodos getters e setters não são obrigatórios neste caso, mas caso eles existam, serão
ignorados pelo provedor JPA.
É recomendado que os atributos tenham o modificador de acesso protegido, privado ou padrão.
O modificador público não é recomendado, pois expõe demais os atributos para qualquer outra 
classe.

Lazy loading
Podemos definir a estratégia de carregamento de relacionamentos de entidades, podendo ser 
lazy(tardia) ou eager(ansiosa).

Operações em cascata.
As operações chamadas nos EntityManagers são aplicadas na entidade informada como parâmetro,
por padrão.

Persistência em cascata.
Em diversas situações, quando persistimos uma entidade, queremos também que seus relacionamentos
sejam persistidos.A JPA fornece um mecanismo para facilitar a persistência de entidades e seus
relacionamentos transientes, sempre que o método persist for chamado.Esse recurso se chama cascade.
Para configurá-lo, basta adicionar uma propriedade cascade na anotação de relacionamento e definir o 
valor CascadeType.PERSIST.

@ManyToOne(optinal = false, cascade = CascadeType.PERSIST)
private Categoria categoria;

Desta forma ao persistir um produto a categoria será persistida também automaticamente.
As operações do EntityManager são identificadas pela enumeração CascadeType com as 
constantes PERSIST,REFRESH,REMOVE,MERGE, DETACH, ALL.


Concorrencia e Locking
Uma das formas de resolver o problema de concorrencia é usando locking
otimista.
	Este tipo de locking tem como filosofia que, dificilmente outro EntityManager
estará fazendo uma alteração no mesmo objeto ao mesmo tempo, ou seja, é uma
estrategia otimista que entende que o problema de concorrencia é uma exceção.
  
  No momento que uma alteração for sincronizada com o banco de dados, o prov-
vedor JPA verifica se o objeto foi alterado por outra transação e lança uma
exceção OptimisticLoclException, caso exista concorrencia.
 
Mas como o provedor JPA sabe se houve uma alteração no objeto?
 
A resposta é que o provedor mantém um controle de versão em um atributo da 
entidade. Precisamos mapear uma propriedade para armazenar a versão da enti-
dade, usando a anotação @Version.


JPQL
A JPQL(Java Persistence Query Language) é a linguagem de consulta padrão da JPA,
que permite escrever consultas portáveis, que funcionam independente do SGBD.

Essa linguagem de query usa uma sintaxe parecida com a SQL para selecionar objetos e
valores de entidades e os relacionamentos entre elas.


Criteria API
A criteria API da JPA é usada para definir queries dinâmicas, criadas a partir de objetos que 
definem uma consulta, ao invés de puramente texto, como JPQL.

A principal vantagem da Criteria API é poder construir consultas programaticamente, de forma
elegante e com maior integração com a linguagem Java.






