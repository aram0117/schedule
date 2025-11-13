package com.schedule.controller;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.CreateScheduleResponse;
import com.schedule.dto.GetScheduleResponse;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 파이널 필드를 매개변수로 받는 생성자를 자동생성하는 어노테이션
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 스케쥴 생성
    @PostMapping("/schedules")
    public CreateScheduleResponse creatSchedule(@RequestBody CreateScheduleRequest request) {
        return scheduleService.createSchedule(request);
    }

    // 다 건 조회
    @GetMapping("/schedules")
    public List<GetScheduleResponse> getSchedules() {
        return scheduleService.getAll();
    }

    // 다 건 조회
    @GetMapping("/schedules/{scheduleId}")
    public GetScheduleResponse getSchedule(@PathVariable Long scheduleId) {
        return  scheduleService.getOne(scheduleId);
    }
}
