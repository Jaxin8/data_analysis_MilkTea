package com.dark.api.mapper;


import com.dark.api.vo.NumberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NumberMapper {
    List<NumberVO> find();

}
