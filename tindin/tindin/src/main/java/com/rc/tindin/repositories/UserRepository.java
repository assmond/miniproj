package com.rc.tindin.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.rc.tindin.model.User;

@Repository
public class UserRepository {
    final String CREATE_USER = "INSERT INTO USER(username, password, gender, company, job, url) VALUES(?,?,?,?,?,?)";
    final String GET_ALL_USERS = "SELECT * FROM USER";
    final String LOGIN_USER = "SELECT username FROM USER WHERE username = ? and password = ?";
    final String GET_SELECTED_USER = "SELECT * FROM USER where username = ?";

    @Autowired
    private JdbcTemplate template;

    public boolean createUser(String username, String password, String gender, String company, String jobTitle, String linkedinUrl) {
        try{
            template.update(CREATE_USER, username, password, gender, company, jobTitle, linkedinUrl);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password){
        try {

			String selectedUsername = template.queryForObject(LOGIN_USER, String.class, new Object[] {
                username, password });
            
			return true;
			
		} catch (Exception e) {
			return false;
		}
    }

    public List<String> getAllUsernames(){
        List<String> usernameList = new ArrayList<String>();

        List<Map<String, Object>> rows = template.queryForList(GET_ALL_USERS);

        for (Map row : rows) {
            usernameList.add((String)row.get("username"));
        }

        return usernameList;
    }

    public User getSelectedUser(String username){
		return template.queryForObject(GET_SELECTED_USER, new UserRowMapper(), new Object[]{username});
    }
}
