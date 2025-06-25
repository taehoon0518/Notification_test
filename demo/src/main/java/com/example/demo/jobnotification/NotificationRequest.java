package com.example.demo.jobnotification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String jobName;
    private String recipient;
    private String eventType;
    private String channel;
    private String webhookUrl;
}