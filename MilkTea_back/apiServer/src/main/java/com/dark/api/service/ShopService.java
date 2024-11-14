package com.dark.api.service;

import com.dark.api.dto.ShopDTO;
import com.dark.api.query.ShopQuery;
import com.dark.api.utils.PageResult;
import com.dark.api.vo.ShopVO;

import java.util.List;

public interface ShopService {
    void add(ShopDTO shopDTO);

    void addRandomShop();

    PageResult<ShopVO> find(ShopQuery shopQuery);

    ShopVO findById(long id);

    void update(long id, ShopDTO shopDTO);

    void deleteById(long id);

    void updateStatus(long id, Byte status);

}
