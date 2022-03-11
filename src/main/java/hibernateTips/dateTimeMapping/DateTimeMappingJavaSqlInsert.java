package hibernateTips.dateTimeMapping;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.EntityManager;

import algaworksEbook.JpaUtil;

public class DateTimeMappingJavaSqlInsert {
	
	
	public static void main(String[] args) {
		EntityManager em = JpaUtil.getEntityManager();
		
		DateTimeMappingJavaSql d = new DateTimeMappingJavaSql();
		d.setSqlDate(new java.sql.Date(2019,6,18));
		d.setSqlTime(new Time(15,05,30));
		d.setSqlTimestamp(new Timestamp(2019,6,18,15,05,30,0));
		
		em.persist(d);
		
	}

}
