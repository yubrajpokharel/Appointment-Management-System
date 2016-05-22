package com.cs4.appointmentManagement.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cs4.appointmentManagement.dao.UserDao;
import com.cs4.appointmentManagement.domain.User;

/**
 * This class is used to implements the DB related tasks related to the user
 *
 */
@Repository
public class UseDaoImpl extends GenericDaoImpl<User> implements UserDao{
	public UseDaoImpl() {
		super.setDaoType(User.class);
	}

	/**
	 * this method is used to find the user by username
	 * @param username
	 * @return User Object
	 */
	@Override
	public User findByUsername(String username) {
		Query query = entityManager.createQuery("SELECT u FROM "+daoType.getName()+" u INNER JOIN u.userCredentials c WHERE "
				+ "c.userName = :username)");
		query.setParameter("username", username);
		return (User) query.getSingleResult();
	}
	
	/**
	 * this method is used to search the list of users by the search text
	 * @param name 
	 * @return user list
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> search(String name) {
		Query query = entityManager.createQuery("SELECT u FROM "+daoType.getName()+" u WHERE u.fname like :name OR u.lname like :name");
		query.setParameter("name", "%"+name+"%");
		return (List<User>) query.getResultList();
	}
	
	/**
	 * this method is used to find the user id using username
	 * @param username
	 * @return user id
	 */
	@Override
	public Long findUserID(String username){
		System.out.println("DAO impl : "+username);
		User u = (User) findByUsername(username);
		System.out.println("I GOT it : "+u.getId());
		return (Long)u.getId();
	}
	
	/**
	 * this method is used to find the user by username
	 * @param id
	 * @return User - Obj
	 */
	@Override
		public User findUserByID(Long id) {
			Query query = entityManager.createQuery("SELECT u from "+daoType.getName()+" u WHERE u.id = :id");
			query.setParameter("id", id);
			return (User) query.getSingleResult();
		}
	
	
}
