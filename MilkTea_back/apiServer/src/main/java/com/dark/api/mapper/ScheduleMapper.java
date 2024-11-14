package com.dark.api.mapper;

import com.dark.api.vo.ScheduleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleVO> find();
}
