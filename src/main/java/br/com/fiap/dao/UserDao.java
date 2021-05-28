package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
import br.com.fiap.utils.JPAUtil;

public class UserDao {
	
	public void save(User user) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();		
	}
	
	public List<User> getAll(){
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
	    List<User> users = query.getResultList();
	    return users;
		
	
	}
	
	public User findById(long id) {
		EntityManager em = JPAUtil.getEntityManager();
		User user = em.find(User.class, id);
		return user;
	}
	
	public User findByEmail(String email) {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<User> query = 
				em.createQuery("from User u where u.email = :email", User.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}
	
	public void update(User user) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
	}

	public boolean exist(User user) {
		EntityManager manager = JPAUtil.getEntityManager();
		TypedQuery<User> query = manager.createQuery("SELECT u FROM User u "
							+ "WHERE u.email = :email AND u.password = :password"
							, User.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		try {
			query.getSingleResult();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void delete(User user) {
		EntityManager manager = JPAUtil.getEntityManager();
		User u = manager.find(User.class, user.getId());
		manager.getTransaction().begin();
		manager.remove(user);
		manager.flush();
		manager.getTransaction().commit();
		
	}
}
