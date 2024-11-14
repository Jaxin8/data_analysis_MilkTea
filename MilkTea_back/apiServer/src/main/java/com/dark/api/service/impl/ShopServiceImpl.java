package com.dark.api.service.impl;


import com.dark.api.dto.ShopDTO;
import com.dark.api.mapper.ShopMapper;
import com.dark.api.pojo.Shop;
import com.dark.api.query.ShopQuery;
import com.dark.api.service.ShopService;
import com.dark.api.utils.PageResult;
import com.dark.api.vo.ShopVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    ShopMapper shopMapper;

    @Override
    public void add(ShopDTO shopDTO) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopDTO,shop);

        shopMapper.add(shop);
    }

    @Override
    public void addRandomShop() {
        for(int i=0;i<100;i++) {
            Shop shop = new Shop();

            Long id = shopMapper.getMaxId() + 1;
            String province = shopMapper.getProvince();


            Random random = new Random();
            int saleNumber = 10000 + random.nextInt(40001);


            shop.setShopName("暗黑奶茶" + "-" + id + "店");
            shop.setAmount(saleNumber * 17);
            shop.setSaleNumber(saleNumber);
            shop.setProvince(province);
            shop.setAddress(province + "-" + id + "号");
            shopMapper.add(shop);
        }
    }

    @Override
    public PageResult<ShopVO> find(ShopQuery shopQuery) {
        //设置是否需要分页
        PageHelper.startPage(shopQuery.getPage(),shopQuery.getPageSize());  //告诉PageHelper此处需要分页

        com.github.pagehelper.Page<ShopVO> page = (com.github.pagehelper.Page<ShopVO>) shopMapper.find(shopQuery);

        PageInfo<ShopVO> pageInfo = page.toPageInfo();


        List<ShopVO> shopVOList = pageInfo.getList();
        long total = pageInfo.getTotal();

        PageResult<ShopVO> pageResult = new PageResult<>();
        pageResult.setTotal(total);
        pageResult.setList(shopVOList);

        return pageResult;
    }

    @Override
    public ShopVO findById(long id) {
        return shopMapper.findById(id);
    }

    @Override
    public void update(long id, ShopDTO shopDTO) {
        Shop shop = new Shop();
        BeanUtils.copyProperties(shopDTO,shop);

        shop.setId(id);
        shopMapper.update(shop);
    }

    @Override
    public void deleteById(long id) {
        shopMapper.deleteById(id);
    }

    @Override
    public void updateStatus(long id, Byte status) {
        Shop shop = new Shop();
        shop.setId(id);
        shopMapper.update(shop);
    }

}
