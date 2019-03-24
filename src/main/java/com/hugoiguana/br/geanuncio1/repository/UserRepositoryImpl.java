package com.hugoiguana.br.geanuncio1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

//import com.querydsl.jpa.impl.JPAQuery;
import com.hugoiguana.br.geanuncio1.models.User;

@Repository
public class UserRepositoryImpl implements CustomizedUserRepository, BaseRepository {

  private final EntityManager entityManager;

  @Autowired
  public UserRepositoryImpl(JpaContext context) {
    this.entityManager = context.getEntityManagerByManagedType(User.class);
  }
	
	public List<User> teste() {
		
		//JPAQuery<?> query = new JPAQuery(entityManager);
		
		
		return null;
	}
	
}
