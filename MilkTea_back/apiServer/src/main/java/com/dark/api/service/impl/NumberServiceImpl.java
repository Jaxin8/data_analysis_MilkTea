package com.dark.api.service.impl;


import com.dark.api.mapper.NumberMapper;
import com.dark.api.service.NumberService;
import com.dark.api.vo.NumberVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NumberServiceImpl implements NumberService {
    @Resource
    NumberMapper numberMapper;

    @Override
    public List<NumberVO> find() {
        return numberMapper.find();
    }

}
