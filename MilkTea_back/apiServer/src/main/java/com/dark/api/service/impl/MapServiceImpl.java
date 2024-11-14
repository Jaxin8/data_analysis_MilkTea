package com.dark.api.service.impl;

import com.dark.api.mapper.MapMapper;
import com.dark.api.pojo.Shop;
import com.dark.api.service.MapService;
import com.dark.api.vo.MapVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

@Service
public class MapServiceImpl implements MapService {

    @Resource
    MapMapper mapMapper;

    @Override
    public void add() {
        for (int i = 0; i <=500; i++) {
            int max = 999, min = 1;
            String[] provinces = {"北京", "上海", "天津", "重庆", "河北", "山西", "辽宁", "吉林", "黑龙江", "江苏", "浙江", "安徽", "福建",
                    "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海",
                    "宁夏", "新疆", "内蒙古", "台湾", "香港", "澳门"};
            long randomNum = System.currentTimeMillis();
            int ran = (int) (randomNum % (max - min) + min);

            Random random = new Random();
            int index = random.nextInt(provinces.length);

            Shop shop = new Shop();

            shop.setShopName(ran+"号店");
            shop.setProvince(provinces[index]);


            int num = random.nextInt(999999-256+1)+256;
            shop.setSaleNumber(num);
            shop.setAmount(num*17);

            mapMapper.add(shop);
        }



//        System.out.println(ran);

    }

    @Override
    public List<MapVO> findShopAmount() {
        return mapMapper.findShopAmount();
    }

    @Override
    public int findAllShopAmount() {
        return mapMapper.findAllShopAmount();
    }

    @Override
    public String findSalesAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String salesAmount = decimalFormat.format(mapMapper.findSalesAmount());
        return salesAmount;
    }
}
