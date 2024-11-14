package com.dark.api.mapper;

import com.dark.api.pojo.Shop;
import com.dark.api.vo.MapVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {
    void add(Shop shop);

    List<MapVO> findShopAmount();

    int findAllShopAmount();

    long findSalesAmount();
}
