package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.utils.JPAUtil;

public class SetupDao {

	public void save(Setup setup) {
		EntityManager manager = JPAUtil.getEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();

	}

	public List<Setup> getAll() {
		EntityManager manager = JPAUtil.getEntityManager();
		String jpql = "SELECT s FROM Setup s";
		TypedQuery<Setup> query = manager.createQuery(jpql, Setup.class);
		List<Setup> setups = query.getResultList();
		return setups;
	}
	
	public List<Setup> getAllFromUser(Long id) {
		EntityManager manager = JPAUtil.getEntityManager();
		TypedQuery<Setup> query = manager.createQuery("SELECT s FROM Setup s WHERE s.owner = :id", Setup.class);
		query.setParameter("id", id);
		List<Setup> setups = query.getResultList();
		return setups;
	}

	public Setup findById(long id) {
		EntityManager manager = JPAUtil.getEntityManager();
		Setup setup = manager.find(Setup.class, id);
		return setup;
	}

	public void update(Setup setup) {
		EntityManager manager = JPAUtil.getEntityManager();
		
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.flush();
		manager.getTransaction().commit();
	}

	public void delete(Setup setup) {
		EntityManager manager = JPAUtil.getEntityManager();
		Setup s = manager.find(Setup.class, setup.getId());
		manager.getTransaction().begin();
		manager.remove(setup);
		manager.flush();
		manager.getTransaction().commit();
		
	}

}
