package com.ray.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ray.config.HibernateSessionFactoryConfig;

public class JpaDAO<T> {
	private SessionFactory sessionFactory;
	private Class<T> _genericClass;
	
	public JpaDAO(Class<T> genericClass) {
		this._genericClass = genericClass;
		this.sessionFactory = HibernateSessionFactoryConfig.getSessionFactory();
	}

	public T create(T genericObjectToInsert) {
		Transaction transaction = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			session.save(genericObjectToInsert);
			
			session.flush();
			
			session.refresh(genericObjectToInsert);
			
			transaction.commit();
			
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		
		return genericObjectToInsert;
	}
	
	
	public T getById(Object genericObjectId) {
		T result = null;
		Transaction transaction = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			Object queryObject = session.get(_genericClass, (Serializable) genericObjectId);
			result = _genericClass.cast(queryObject);
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		
		return result;
	}
	
	
	public T update (T genericObjectToUpdate) {
		Transaction transaction = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			session.update(genericObjectToUpdate);
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		
		return genericObjectToUpdate;
	}
	
	
	public void deleteById(Object genericObjectId) {
		Transaction transaction = null;
		Object queryObject = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			queryObject = session.get(_genericClass, (Serializable) genericObjectId);
			
			if (queryObject != null) {
				session.delete(queryObject);
			}
			
			transaction.commit();
			
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * HIBERNATE Criteria Queries
	 */
	public List<T> getAll() {
		Transaction transaction = null;
		List<T> objectList = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = builder.createQuery(_genericClass);
			Root<T> root = criteria.from(_genericClass); // FROM
			criteria.select(root); /// select *
			
			Query<T> query = session.createQuery(criteria);
			
			objectList = query.getResultList();
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return objectList;
	}
	
	
	public long getTotalRecord() {
		Transaction transaction = null;
		long totalRecord = 0;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
			Root<T> root = criteria.from(_genericClass); // FROM
			criteria.select(builder.count(root)); /// select count(*)
			
			Query<Long> query = session.createQuery(criteria);
			
			totalRecord = query.getSingleResult();
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return totalRecord;
	}
	
	
	
	/**
	 * HQL HIBERNATE query language
	 */
	public List<T> getAllWithHQL(String hql) {
		Transaction transaction = null;
		List<T> objectList = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			/// HQL
			@SuppressWarnings("unchecked")
			Query<T> hqlQuery = session.createNamedQuery(hql);
			objectList = hqlQuery.getResultList();
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return objectList;
	}
	
	
	public long getTotalRecordWithHQL(String hql) {
		Transaction transaction = null;
		long totalRecord = 0;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			Query<Long> hqlQuery = session.createNamedQuery(hql);
			totalRecord = hqlQuery.getSingleResult();
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return totalRecord;
	}
	
	
	public List<T> getByNamedQueryWithParams(String hql, Map<String, Object> params) {
		Transaction transaction = null;
		List<T> objectList = null;
		
		try (Session session = sessionFactory.openSession()) {
			transaction = session.beginTransaction();
			
			@SuppressWarnings("unchecked")
			Query<T> hqlQuery = session.createNamedQuery(hql);
			
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				hqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
			
			objectList = hqlQuery.getResultList();
			
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			ex.printStackTrace();
		}
		return objectList;
	}
}
