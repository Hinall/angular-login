package dao;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import model.User;

public interface UserRepository {
	UserDetails findByEmail(String email);

	void save(Object user);
}
