package com.rc.tindin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoveMatch{
    
    private @Getter @Setter String userOne;
    private @Getter @Setter String userTwo;
    private @Getter @Setter int percentage;
    private @Getter @Setter String result;

    public LoveMatch(String userOne, String userTwo, int percentage, String result) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.percentage = percentage;
        this.result = result;
    }

}
