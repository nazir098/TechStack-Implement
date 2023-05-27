package com.example.demo.model;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Builder
public class CustomerVisitEvent {
    private String customerId;
    private LocalDateTime dateTime;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public CustomerVisitEvent(String customerId, LocalDateTime dateTime) {
        this.customerId = customerId;
        this.dateTime = dateTime;
    }
    public CustomerVisitEvent(String customerId) {
        this.customerId = customerId;
    }
    public CustomerVisitEvent(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public CustomerVisitEvent() {
    }

}