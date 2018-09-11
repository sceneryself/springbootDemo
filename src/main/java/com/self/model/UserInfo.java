package com.self.model;

import javax.persistence.*;
import java.util.Date;
/*
drop table if EXISTS user_infos;
CREATE TABLE user_infos (
	id BIGINT PRIMARY KEY,
	send_id VARCHARACTER (128),
	request TEXT ,
	response TEXT ,
	log_time TIMESTAMP,
	type VARCHARACTER (32)
);
 */

@Entity
@Table(name = "user_infos")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "send_id", length = 128)
    private String sendId;
    @Column(name = "request", columnDefinition = "text")
    private String request;
    @Column(name = "response", columnDefinition = "text")
    private String response;
    @Column(name = "log_time", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    private Date logTime;
    @Column(name = "type", length = 32)
    private String type;

    public UserInfo() {
    }

    public UserInfo(String sendId, String request, String response, String type) {
        this.sendId = sendId;
        this.request = request;
        this.response = response;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
