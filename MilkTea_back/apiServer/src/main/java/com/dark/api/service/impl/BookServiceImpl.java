package com.dark.api.service.impl;


import com.dark.api.dto.BookDto;
import com.dark.api.mapper.BookMapper;
import com.dark.api.service.BookService;
import com.dark.api.utils.PageResult;
import com.dark.api.vo.BookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;


    @Override
    public List<BookDto> selectBookOrderByMonth() {

        int year = LocalDate.now().getYear();

        List<BookDto> list = bookMapper.selectBookOrderByMonth(year);
        //处理数据 totalprice 要除以 x (由于当前数据较小，这里直接除以x，实际应用中需要根据实际数据来调整)

        // 定义月份数组并转换为HashSet以支持快速查找
        Set<Integer> existingMonths = new HashSet<>();
        int[] monthArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        for (int month : monthArray) {
            existingMonths.add(month);
        }
        // 遍历vipDtoList，从existingMonths中移除已出现的月份
        list.forEach(bookDto -> existingMonths.remove(bookDto.getMonth()));
        // 现在existingMonths包含未出现的月份
        // 把缺少的月份添加到返回的数据里
        if(!existingMonths.isEmpty()){
            existingMonths.forEach(month -> {
                BookDto missingMonthDto = new BookDto(); // 假设VipDto有一个接受月份和total的构造器
                missingMonthDto.setMonth(month);
                missingMonthDto.setTotalPrice(0);
                list.add(missingMonthDto);
            });
        }
        // 根据月份排序
        Collections.sort(list, Comparator.comparingInt(BookDto::getMonth));
        return list;
    }

@Override
    public PageResult<BookVO> find() {

        PageResult<BookVO> pageResult = new PageResult<>();

        List<BookVO> ProjectVOList = bookMapper.find();
        pageResult.setList(ProjectVOList);

        return pageResult;
    }
}
