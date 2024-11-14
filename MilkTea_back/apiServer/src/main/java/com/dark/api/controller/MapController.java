package com.dark.api.controller;

import com.dark.api.service.MapService;
import com.dark.api.utils.Result;
import com.dark.api.vo.MapVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "地图接口")
@RestController
@RequestMapping("/Map")
public class MapController {

    @Resource
    MapService mapService;

    @ApiOperation("加盟")
    @PutMapping
    public Result add( ) {
        mapService.add();
        return Result.ok("添加成功");
    }

    @ApiOperation("获取门店数量")
    @GetMapping("/MapData/ProvinceNum")
    public Result getShopAmonut() {
        List<MapVO> mapVOList = mapService.findShopAmount();
        return Result.ok("查询成功", mapVOList);
    }

    @ApiOperation("获取门店总数量")
    @GetMapping("/MapData/AllNum")
    public Result getAllShopAmonut() {
        int allShopAmount = mapService.findAllShopAmount();
        return Result.ok("查询成功", allShopAmount);
    }

    @ApiOperation("获取总营业额")
    @GetMapping("/MapData/SalesAmount")
    public Result getSalesAmount() {
        String salesAmount = mapService.findSalesAmount();
        return Result.ok("查询成功", salesAmount);
    }
}
