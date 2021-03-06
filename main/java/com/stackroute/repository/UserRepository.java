package com.stackroute.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.User;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	
	List<User> findByUsernameAndPassword(String username,String password);
	List<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	
}
