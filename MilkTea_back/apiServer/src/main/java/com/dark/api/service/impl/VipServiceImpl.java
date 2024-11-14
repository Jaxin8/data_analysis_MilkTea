package com.dark.api.service.impl;


import com.dark.api.dto.VipDto;
import com.dark.api.mapper.VipMapper;
import com.dark.api.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;


    @Override
    public List<VipDto> selectVipOrderByMonth() {

        int year = LocalDate.now().getYear();

        List<VipDto> list = vipMapper.selectVipOrderByMonth(year);
        // 定义月份数组并转换为HashSet以支持快速查找
        Set<Integer> existingMonths = new HashSet<>();
        int[] monthArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int month : monthArray) {
            existingMonths.add(month);
        }
        // 遍历vipDtoList，从existingMonths中移除已出现的月份
        list.forEach(vipDto -> existingMonths.remove(vipDto.getMonth()));
        // 现在existingMonths包含未出现的月份
        // 把缺少的月份添加到返回的数据里
        if(!existingMonths.isEmpty()){
            existingMonths.forEach(month -> {
                VipDto missingMonthDto = new VipDto(); // 假设VipDto有一个接受月份和total的构造器
                missingMonthDto.setMonth(month);
                missingMonthDto.setTotal(0);
                list.add(missingMonthDto);
            });
        }
        // 根据月份排序
        list.sort(Comparator.comparingInt(VipDto::getMonth));
        return list;
    }
}
