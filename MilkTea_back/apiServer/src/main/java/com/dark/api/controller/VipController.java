package com.dark.api.controller;


import com.dark.api.dto.VipDto;
import com.dark.api.service.VipService;
import com.dark.api.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/vip")
public class VipController {
    @Resource
    private VipService vipService;

    @GetMapping("/vipOrderByMonth")
    public Result<List<VipDto>> selectVipOrderByMonth() {
        List<VipDto> vipDtoList = vipService.selectVipOrderByMonth();
        return Result.ok("查询成功",vipDtoList);
    }
}
