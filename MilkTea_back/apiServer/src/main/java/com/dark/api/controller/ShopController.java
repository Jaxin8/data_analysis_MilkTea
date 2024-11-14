package com.dark.api.controller;


import com.dark.api.constant.StatusConst;
import com.dark.api.dto.ShopDTO;
import com.dark.api.exception.ParamException;
import com.dark.api.query.ShopQuery;
import com.dark.api.service.ShopService;
import com.dark.api.utils.PageResult;
import com.dark.api.utils.Result;
import com.dark.api.vo.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "店铺接口")
@RestController
@RequestMapping("/admin/shop")
public class ShopController {

    @Resource
    ShopService shopService;

    @ApiOperation("查询店铺")
    @GetMapping
    public Result<List<ShopVO>> find(ShopQuery shopQuery){ //shopQuery封装了查询的条件
        PageResult<ShopVO> pageResult = shopService.find(shopQuery);
        return Result.ok("成功", pageResult.getList(), pageResult.getTotal());
    }

    @ApiOperation("添加店铺")
    @PostMapping
    public Result add(ShopDTO shopDTO){
        shopService.add(shopDTO);
        return Result.ok("添加成功");
    }

    @ApiOperation("随机添加店铺")
    @PostMapping("/random")
    public Result addRandomShop(){
        shopService.addRandomShop();
        return Result.ok("添加成功");
    }

    @ApiOperation("根据id查询店铺")
    @GetMapping("/{id}")
    public Result findById(@PathVariable long id){
        ShopVO shopVO = shopService.findById(id);
        return Result.ok("查询成功",shopVO);
    }



    @ApiOperation("编辑店铺")
    @PutMapping("/{id}")
    public Result update(@PathVariable long id,ShopDTO shopDTO){ //@RequestBody将前端传递的json格式的请求参数转为对象
        shopService.update(id,shopDTO);
        return Result.ok("修改成功");
    }




    @ApiOperation("禁用启动店铺")
    @PutMapping("/{id}/status")
    public Result updateStatus(@PathVariable long id,String status){
        if(StatusConst.INACTIVE_STATUS_NAME.equals(status)){
            shopService.updateStatus(id,StatusConst.INACTIVE_STATUS);
        }else if(StatusConst.ACTIVE_STATUS_NAME.equals(status)){
            shopService.updateStatus(id,StatusConst.ACTIVE_STATUS);
        }else{
            throw new ParamException("参数错误");
        }
        return Result.ok("修改状态成功");
    }




    @ApiOperation("删除店铺")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable long id){
        shopService.deleteById(id);
        return Result.ok("删除成功");
    }
}
