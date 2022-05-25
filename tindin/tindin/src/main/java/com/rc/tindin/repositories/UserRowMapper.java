package com.rc.tindin.repositories;

import org.springframework.jdbc.core.RowMapper;
import com.rc.tindin.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new User(rs.getInt("userId"),rs.getString("username"),rs.getString("gender"),rs.getString("company"),rs.getString("job"),rs.getString("url"));
    }
}
