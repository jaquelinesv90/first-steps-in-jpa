package hibernateTips.enumMapping.attributeConverter;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import algaworksEbook.JpaUtil;
import hibernateTips.enumMapping.attributeConverter.business.TripBean;
import hibernateTips.enumMapping.attributeConverter.enums.Vehicle;

public class Searching {
	
	public static void main(String[] args) {
		
		EntityManager em = JpaUtil.getEntityManager();
		
		TypedQuery<TripBean> q = em.createQuery("SELECT t FROM Trip t WHERE t.vehicle = :v", TripBean.class);
		q.setParameter("v", Vehicle.PLANE);
		List<TripBean> trips = q.getResultList();
	}

}
