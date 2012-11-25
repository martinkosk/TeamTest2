package ee.itcollege.borderproject.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ee.itcollege.borderproject.dao.IncidentDao;
import ee.itcollege.borderproject.model.Incident;

@Repository
public class IncidentDaoJpa implements IncidentDao{

	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Team11BorderDb");
	
	@Override
	public List<Incident> getIncidents() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			TypedQuery<Incident> q = em.createNamedQuery("Incident.findAll",
					Incident.class);
			List<Incident> incidents = q.getResultList();
			return incidents;
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void saveIncident(Incident incident) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(incident);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public void saveIncidents(List<Incident> incidents) {
		for (Incident incident : incidents)
			saveIncident(incident);
	}

	@Override
	public Incident searchIncident(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			return em.find(Incident.class, id);
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void updateIncident(Incident incident) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(incident);
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

	@Override
	public void deleteIncident(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();			
			Incident incident = em.find(Incident.class, id);
			
			if (incident != null)
				em.remove(incident);
			
			em.getTransaction().commit();
		} 
		finally {
			em.close();
		}
	}

}
