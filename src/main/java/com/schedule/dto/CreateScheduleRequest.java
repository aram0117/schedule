package com.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    // 스케쥴 필드 요청
     private String userName;
     private String title;
     private String content;
}
