package com.clinic.data.repos;

import java.util.List;

import com.clinic.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public List<User> findByUsername(String username);
}
