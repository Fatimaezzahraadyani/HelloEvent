package com.helloevent.backend.dto;

import com.helloevent.backend.model.Status;

import java.util.Date;

public class EventRequest {
    private String name;
    private String description;
    private Date date;
    private String lieu;
    private Status status;
    private long userId;
    private long categoryId;

    public EventRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "EventRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", lieu='" + lieu + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
