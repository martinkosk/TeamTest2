package ee.itcollege.borderproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ee.itcollege.borderproject.dao.BorderStationDao;
import ee.itcollege.borderproject.model.BorderStation;

@Repository
public class BorderStationDaoJpa implements BorderStationDao{

	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Team11BorderDb");
	
	@Override
	public List<BorderStation> getBorderStations() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<BorderStation> q = em.createNamedQuery("BorderStation.findAll",
					BorderStation.class);
			List<BorderStation> borderStations = q.getResultList();
			return borderStations;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void saveBorderStation(BorderStation borderStation) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(borderStation);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void saveBorderStations(List<BorderStation> borderStations) {
		for (BorderStation borderStation : borderStations)
			saveBorderStation(borderStation);
	}

	@Override
	public BorderStation searchBorderStation(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			return em.find(BorderStation.class, id);
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void updateBorderStation(BorderStation borderStation) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(borderStation);
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void deleteBorderStation(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();			
			BorderStation borderStation = em.find(BorderStation.class, id);
			
			if (borderStation != null)
				em.remove(borderStation);
			
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

}
