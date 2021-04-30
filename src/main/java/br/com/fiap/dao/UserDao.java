package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;
import br.com.fiap.utils.JPAUtil;

public class UserDao {
	
	public void save(User user) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public List<User> getAll(){
		EntityManager em = JPAUtil.getEntityManager();
		String jpql = "SELECT s FROM User s";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
	    List<User> users = query.getResultList();
	    em.close();
	    return users;
		
	
	}

}
