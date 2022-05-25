package com.rc.tindin.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.rc.tindin.model.*;

@Repository
public class LoveMatchRepository {

    final String INSERT_MATCH = "insert into LoveMatch (firstUser, secondUser, percentage, result) values(?,?,?,?)";
    final String FIND_ALL_MATCHES = "select * from LoveMatch where firstUser= ?";

    @Autowired
    private JdbcTemplate template;

    public void insertMatch(LoveMatch lm){
        template.update(INSERT_MATCH, lm.getUserOne(), lm.getUserTwo(), lm.getPercentage(), lm.getResult());
    }

    public List<LoveMatch> getMatches(String username){

        List<LoveMatch> loveMatchList = new ArrayList<LoveMatch>();
        List<Map<String, Object>> rows = template.queryForList(FIND_ALL_MATCHES, username);
        for (Map row: rows){
            String userOne = (String)row.get("firstUser");
            String userTwo = (String)row.get("secondUser");
            int percentage = (Integer)row.get("percentage");
            String result = (String)row.get("result");

            loveMatchList.add(new LoveMatch(userOne, userTwo, percentage, result));
        }

        return loveMatchList;
    }

    public List<String> getMatchedUsers(String username){
        List<String> matchList = new ArrayList<String>();
        List<Map<String, Object>> rows = template.queryForList(FIND_ALL_MATCHES, username);
        for (Map row: rows){
            String userTwo = (String)row.get("secondUser");

            matchList.add(userTwo);
        }

        return matchList;
    }

}