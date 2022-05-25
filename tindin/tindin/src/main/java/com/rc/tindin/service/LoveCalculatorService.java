package com.rc.tindin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.StringReader;
import java.util.List;

import com.rc.tindin.model.*;
import com.rc.tindin.repositories.*;

@Service
public class LoveCalculatorService {

    @Autowired
    LoveMatchRepository lm;
    
    public LoveMatch matchUsers(String fname, String sname){
        String apiUrl = UriComponentsBuilder.fromUriString("https://love-calculator.p.rapidapi.com/getPercentage").queryParam("sname", sname).queryParam("fname", fname).toUriString();

        RestTemplate template = new RestTemplate();
        RequestEntity request = RequestEntity.get(apiUrl).build();
        LoveMatch match = null;

        try{
            ResponseEntity<String> response = template.exchange(request,String.class);
            if (response.getStatusCodeValue() == 200){
                String jsonBody = response.getBody();
                JsonObject respJson = Json.createReader(new StringReader(jsonBody)).readObject();
                match = new LoveMatch(respJson.get("fname").toString(), respJson.get("sname").toString(), Integer.parseInt(respJson.get("percentage").toString()), respJson.get("result").toString());
            }
            else {
                System.out.println("Did not receive expected response of status 200. Got response of " + response.getStatusCodeValue());
            }
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        } catch (IllegalStateException e){
            System.out.println(e.getMessage());
        }

        return match;
    }

    public List<LoveMatch> retrieveMatches(String username){
        return lm.getMatches(username);
    }

    public List<String> retrieveMatchedUsers(String username){
        return lm.getMatchedUsers(username);
    }

}
