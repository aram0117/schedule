package com.schedule.service;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.CreateScheduleResponse;
import com.schedule.dto.GetScheduleResponse;
import com.schedule.entity.ScheduleEntity;
import com.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
// 파이널 필드를 매개변수로 받는 생성자를 자동생성하는 어노테이션
@RequiredArgsConstructor
public class ScheduleService {
    // 스케쥴 레포지토리 필드
    private final ScheduleRepository scheduleRepository;

    // 스케쥴 생성
    @Transactional
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        // 스케쥴 요청
        ScheduleEntity schedule = new ScheduleEntity(
                request.getUserName(),
                request.getTitle(),
                request.getContent()
        );
        // 스케쥴 DB에 스케쥴 저장
        ScheduleEntity saveSchedule = scheduleRepository.save(schedule);
        // 스케쥴 반환
        return new CreateScheduleResponse(
                saveSchedule.getScheduleId(),
                saveSchedule.getUserName(),
                saveSchedule.getTitle(),
                saveSchedule.getContent(),
                saveSchedule.getCreatedAt(),
                saveSchedule.getModifiedAt()
        );
    }

    // 스케쥴 다 건 조회
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll() {
        // 스케쥴 전체 찾기
        List<ScheduleEntity> schedules = scheduleRepository.findAll();
        // 스케쥴 전체 조회르 위한 리스트 선언
        List<GetScheduleResponse> scheduleDtoList = new ArrayList<>();
        for(ScheduleEntity schedule : schedules) {
            GetScheduleResponse scheduleDto = new GetScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getUserName(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            // 반환할 스케쥴들을 리스트에 추가
            scheduleDtoList.add(scheduleDto);
        }
        // 스케쥴 반환
        return scheduleDtoList;
    }

    // 스케쥴 단 건 조히
    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long scheduleId){
        // 스케쥴 아이디로 스케쥴 찾기
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("없는 스케쥴 입니다")
        );
        // 스케쥴 아이디로 찾은 스케쥴 반환
        return new GetScheduleResponse(
                schedule.getScheduleId(),
                schedule.getUserName(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
