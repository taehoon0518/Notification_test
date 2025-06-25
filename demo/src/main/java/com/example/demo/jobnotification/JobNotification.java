package com.example.demo.jobnotification;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "job_notification")
public class JobNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobName;

    private String recipient;

    private String eventType; // BUILD_SUCCESS, BUILD_FAILURE 등

    private Boolean shouldNotify;

    private String channel; // slack, discord

    private String webhookUrl;
}