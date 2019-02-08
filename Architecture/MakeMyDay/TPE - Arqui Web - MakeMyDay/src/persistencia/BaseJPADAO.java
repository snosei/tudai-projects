package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class BaseJPADAO<E> implements DAO<E>{

	Class<E> entityClass;
	
	public BaseJPADAO(Class<E> entityClass){
		this.entityClass=entityClass;
	}
	
	public E persist(E entity) {
		EntityManager entityManager=EntityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}

	public E findById(int id) {
		EntityManager entityManager=EntityManagerFactory.createEntityManager();
		E entity=entityManager.find(entityClass, id);
		entityManager.close();
		return entity;
	}
	
	public List<E> findAll(){
		System.out.println(entityClass.getSimpleName());
		EntityManager entityManager=EntityManagerFactory.createEntityManager();
		Query entity = entityManager.createQuery("SELECT a FROM "+entityClass.getSimpleName()+" a");
		List<E> results = entity.getResultList();
		entityManager.close();
		return results;
	}

}
