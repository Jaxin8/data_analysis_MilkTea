package com.dark.api.service;

import com.dark.api.vo.MapVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MapService {
    void add();

    List<MapVO> findShopAmount();

    int findAllShopAmount();

    String findSalesAmount();
}
