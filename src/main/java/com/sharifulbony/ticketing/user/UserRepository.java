package com.sharifulbony.ticketing.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	UserEntity findByUsername(String username);
	
}