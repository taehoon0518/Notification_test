package com.example.demo.jobnotification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JobNotificationRepository notificationRepository;

    public JobNotification save(NotificationRequest request) {
        JobNotification notification = JobNotification.builder()
                .jobName(request.getJobName())
                .recipient(request.getRecipient())
                .eventType(request.getEventType())
                .shouldNotify(true)
                .channel(request.getChannel())
                .webhookUrl(request.getWebhookUrl())
                .build();

        return notificationRepository.save(notification);
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<JobNotification> findAll() {
        return notificationRepository.findAll();
    }

    public void sendNotification(String jobName, String eventType, String message) {
        List<JobNotification> notifications = notificationRepository.findAll()
                .stream()
                .filter(n -> n.getJobName().equals(jobName) && n.getEventType().equals(eventType) && n.getShouldNotify())
                .toList();

        WebClient webClient = WebClient.create();

        for (JobNotification notification : notifications) {
            webClient.post()
                    .uri(notification.getWebhookUrl())
                    .header("Content-Type", "application/json")
                    .bodyValue("{\"content\":\"" + message + "\"}")
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe();
        }
    }
}