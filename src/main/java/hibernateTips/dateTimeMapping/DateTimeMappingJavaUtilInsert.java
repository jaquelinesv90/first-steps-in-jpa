package hibernateTips.dateTimeMapping;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;

import algaworksEbook.JpaUtil;

public class DateTimeMappingJavaUtilInsert {
	
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		DateTimeMappingJavaUtil d = new DateTimeMappingJavaUtil();
		d.setUtilDate(new Date(2019,6,18));
		d.setUtilCalendar(new GregorianCalendar(2019,6,18));
		
		em.persist(d);
		
	}

}
