package com.example.demo.jobnotification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // 웹훅 등록
    @PostMapping("/save")
    public JobNotification create(@RequestBody NotificationRequest request) {
        return notificationService.save(request);
    }

    // 웹훅 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }

    // 웹훅 전체 조회
    @GetMapping("/list")
    public List<JobNotification> findAll() {
        return notificationService.findAll();
    }

    // 알림 전송
    @PostMapping("/notify")
    public void sendNotification(@RequestParam String jobName,
                                 @RequestParam String eventType,
                                 @RequestParam String message) {
        notificationService.sendNotification(jobName, eventType, message);
    }
}