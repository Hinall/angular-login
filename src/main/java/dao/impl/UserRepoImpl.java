package dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import dao.UserRepository;
import io.jsonwebtoken.io.IOException;
import model.User;
import utils.SPUtility;
import javax.sql.DataSource;
@Repository
public class UserRepoImpl implements  UserRepository {

	 

	  @Autowired
		private JdbcTemplate jdbcTemplate;

		@Autowired
		ObjectMapper ObjectMapper;

		@Autowired
		PasswordEncoder encoder;

//		public UserRepoImpl(DataSource dataSource) {
//			this.setDataSource(dataSource);
//		}

		public String findByEmail(String json) {
		    String sql = "SELECT * FROM users WHERE email = ?";
		    String result = "";
		    try {
		    	result = jdbcTemplate.queryForObject(sql, new Object[]{json},String.class );
		        if (result != null && !result.isEmpty()) {
					return result;
				} else {
					return null;
				}
		    } catch (Exception e) {
		        // Log the exception (optional)
		        System.err.println("Error finding user by email: " + e.getMessage());

		     }
			return result;
		
		}
		
		
		@Override
		public void save(Object user) {
			// TODO Auto-generated method stub
			
		}
}
