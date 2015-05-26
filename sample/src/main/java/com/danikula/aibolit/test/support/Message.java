package com.danikula.aibolit.test.support;

import java.util.Date;

public class Message {
    
    private Date time;
    
    private String text;

    public Message(Date time, String text) {
        this.time = time;
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

}
