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
    // 스케쥴 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private String userName;
    private String title;
    private String content;

    // 유저가 있어야 스케쥴이 존재할수 있다
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // jpa null 허용
    @JoinColumn(name = "user_id", nullable = false) // db null 허용
    private UserEntity userEntity;



    public ScheduleEntity(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }

    // 업데이트 구현시 반환
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
