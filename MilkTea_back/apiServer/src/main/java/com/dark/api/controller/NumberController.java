package com.dark.api.controller;



import com.dark.api.service.NumberService;
import com.dark.api.utils.ResultModel;
import com.dark.api.vo.NumberVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/number")
public class NumberController {

    @Resource
    NumberService numberService;

    @ApiOperation("查找数据")
    @GetMapping
    public ResultModel<List<NumberVO>> list(){
        ResultModel<List<NumberVO>> resultModel = new ResultModel<>();
        resultModel.setMsg("成功");
        resultModel.setData(numberService.find());
        return resultModel;

    }



}
