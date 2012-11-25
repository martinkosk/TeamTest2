package ee.itcollege.borderproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ee.itcollege.borderproject.dao.GuardDao;
import ee.itcollege.borderproject.model.Guard;

@Repository
public class GuardDaoJpa implements GuardDao {

	private static final String GUARD_NAME_PARAMETER = "name";
	private static final String GUARD_AGE_PARAMETER = "age";
	
	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Team11BorderDb");

	@Override
	public List<Guard> getGuards() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Guard> q = em.createNamedQuery("Guard.findAll",
					Guard.class);
			List<Guard> guards = q.getResultList();
			return guards;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void saveGuard(Guard guard) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(guard);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void saveGuards(List<Guard> guards) {
		for (Guard guard : guards)
			saveGuard(guard);
	}
	
	@Override
	public Guard searchGuard(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			return em.find(Guard.class, id);
		} 
		finally {
			em.close();
		}
	}

	@Override
	public List<Guard> searchGuards(String name, Integer age) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Guard> q = em.createNamedQuery("Guard.findByNameAndAge",
					Guard.class);
			q.setParameter(GUARD_NAME_PARAMETER, name).setParameter(GUARD_AGE_PARAMETER, age);
			List<Guard> guards = q.getResultList();
			return guards;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public List<Guard> searchGuards(String name) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Guard> q = em.createNamedQuery("Guard.findByName",
					Guard.class);
			q.setParameter(GUARD_NAME_PARAMETER, name);
			List<Guard> guards = q.getResultList();
			return guards;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public List<Guard> searchGuards(Integer age) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Guard> q = em.createNamedQuery("Guard.findByAge",
					Guard.class);
			q.setParameter(GUARD_AGE_PARAMETER, age);
			List<Guard> guards = q.getResultList();
			return guards;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void updateGuard(Guard guard) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(guard);
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void deleteGuard(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();			
			Guard guard = em.find(Guard.class, id);
			
			if (guard != null)
				em.remove(guard);
			
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

}
