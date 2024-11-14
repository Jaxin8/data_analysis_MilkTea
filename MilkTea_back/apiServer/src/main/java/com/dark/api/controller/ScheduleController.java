package com.dark.api.controller;

import com.dark.api.service.ScheduleService;

import com.dark.api.utils.Result;
import com.dark.api.vo.ScheduleVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Resource
    ScheduleService scheduleService;
    @GetMapping
    public Result<List<ScheduleVO>> list(){
        Result<List<ScheduleVO>> result = new Result<>();
        List<ScheduleVO> list = scheduleService.find();
        return result.ok("查询成功",list);

    }


}