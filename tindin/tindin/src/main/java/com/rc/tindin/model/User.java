package com.rc.tindin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private @Getter @Setter int userId;
    private @Getter @Setter String username;
    private @Getter @Setter String gender;
    private @Getter @Setter String company;
    private @Getter @Setter String job;
    private @Getter @Setter String url;

    public User(int userId, String username, String gender, String company, String job, String url) {
        this.userId = userId;
        this.username = username;
        this.gender = gender;
        this.company = company;
        this.job = job;
        this.url = url;
    }


}
