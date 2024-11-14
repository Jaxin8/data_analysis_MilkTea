package com.dark.api.mapper;


import com.dark.api.dto.VipDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VipMapper {


    @Select("SELECT MONTH(create_time) as month, COUNT(*) as total FROM vip WHERE YEAR(create_time) = ${year} GROUP BY month ORDER BY month ASC")
    public List<VipDto> selectVipOrderByMonth(int year);
}
