package com.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleEntity extends BaseEntity {
    // 스케쥴 DB
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String userName;
    private String title;
    private String content;

    public ScheduleEntity(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    // 업데이트 구현시 반환
    public void update(String userName, String title, String content){
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
