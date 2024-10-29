package com.ssafy.test.reservation.model;

public class Reservations {
    private Long id;
    private String userId;
    private Integer resourceId;
    private String startTime;
    private String endTime;
    private String resourceName;

    public Reservations() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() { // 수정된 getter 메서드
        return endTime;
    }

    public void setEndTime(String endTime) { // 수정된 setter 메서드
        this.endTime = endTime;
    }

    public String getResourceName() { // 추가된 getter 메서드
        return resourceName;
    }

    public void setResourceName(String resourceName) { // 추가된 setter 메서드
        this.resourceName = resourceName;
    }
}
