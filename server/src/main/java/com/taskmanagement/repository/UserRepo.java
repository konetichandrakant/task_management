package com.taskmanagement.repository;

import java.util.Optional;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.model.User;

@Repository
public interface UserRepo extends CouchbaseRepository<User, String> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
