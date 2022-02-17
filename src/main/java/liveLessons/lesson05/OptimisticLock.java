package liveLessons.lesson05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * recursos de simultaneidade
 * estrategia de controle de concorrencia
 * É um mecanismo disponibilizado pela JPA, onde várias atualizações feitas nos mesmos dados ao mesmo 
 * tempo não interfiram umas nas outras */
public class OptimisticLock {

	 private static final Integer CALL_LIGHTMAN_ID = 1;

	    public static void main(String[] args) {
	        EntityManagerFactory entityManagerFactory = Persistence
	                .createEntityManagerFactory("Usuarios-PU");

	        EntityManager entityManager = entityManagerFactory.createEntityManager();
	        Employee employee = entityManager.find(Employee.class, CALL_LIGHTMAN_ID);
	        entityManager.close();

	        TelaDeFuncionarios sessao1 =
	                new TelaDeFuncionarios("SESSAO_1", entityManagerFactory,
	                		employee, "Call Lightman Moreira");
	        sessao1.editName();
	        sessao1.submeterFormulario();

	        TelaDeFuncionarios sessao2 =
	                new TelaDeFuncionarios("SESSAO_2", entityManagerFactory,
	                		employee, "Call Lightman Silva");
	        sessao2.editName();
	        sessao2.submeterFormulario();

	        sessao1.atualizarTelaParaVerificarNome();
	        sessao2.atualizarTelaParaVerificarNome();

	        sessao1.fecharTela();
	        sessao2.fecharTela();

	        entityManagerFactory.close();
	    }

	    public static class TelaDeFuncionarios {

	        private final String session;

	        private final EntityManager entityManager;

	        private final Employee employee;

	        private final String newName;

	        public TelaDeFuncionarios(String sessao, EntityManagerFactory entityManagerFactory,
	        		Employee funcionario, String novoNome) {
	            this.session = sessao;
	            this.entityManager = entityManagerFactory.createEntityManager();
	            this.employee = funcionario;
	            this.newName = novoNome;
	        }

	        public void editName() {
	            employee.setName(newName);
	        }

	        public void submeterFormulario() {
	            System.out.println(session + ": Iniciando tentativa de atualaizar funcionário.");

	            try {
	                entityManager.getTransaction().begin();
	                entityManager.merge(employee);
	                entityManager.getTransaction().commit();

	                System.out.println(session + ": Funcionário foi atualizado. ");
	            } catch (Exception e) {
	                System.out.println(session + ": Erro na atualização do funcionário. MSG: " + e.getMessage());

	                throw e;
	            }
	        }

	        public void atualizarTelaParaVerificarNome() {
	            entityManager.clear();

	            Employee employee = entityManager.find(Employee.class, CALL_LIGHTMAN_ID);

	            System.out.println(session + ": Tela da sessão " + session + " atualizada.");
	            if (newName.equals(employee.getName())) {
	                System.out.println(session + ": Bom... Foi atualizado certinho. " +
	                        "Agora vou continuar meu trabalho.");
	            } else {
	                System.out.println(session + ": Ueh! Não tinha deixado o nome " + employee.getName() +
	                        " eu tenho certeza que coloquei " + newName);
	            }
	        }

	        public void fecharTela() {
	            entityManager.close();
	        }
	    }
	
}
