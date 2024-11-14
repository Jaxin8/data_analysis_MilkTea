package com.dark.api.service.impl;

import com.dark.api.mapper.ScheduleMapper;
import com.dark.api.service.ScheduleService;
import com.dark.api.vo.ScheduleVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    ScheduleMapper scheduleMapper;

    @Override
    public List<ScheduleVO> find() {
        return scheduleMapper.find();
    }
}
